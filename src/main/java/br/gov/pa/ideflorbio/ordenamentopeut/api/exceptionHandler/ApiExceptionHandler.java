package br.gov.pa.ideflorbio.ordenamentopeut.api.exceptionHandler;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um ERRO inesperado no sistema. Tente novamente e, se o erro persistir"
			+ ", entre em contato o NTI do IDEFLOR-Bio.";
	
	
	//1____________________________________________________
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		
				Problem problem = createProblemBuilder(status, problemType, detail)
						.userMessage(detail)
						.timestamp(LocalDateTime.now())
						.build();
		
		
					return handleExceptionInternal(ex, problem , new HttpHeaders(), status , request);
		
	}
	//2_____________________________________________________
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
				Problem problem = createProblemBuilder(status, problemType, detail)
						.userMessage(detail)
						.timestamp(LocalDateTime.now())
						.build();
		
		
					return handleExceptionInternal(ex, problem , new HttpHeaders(), status , request);
	}
	
	//3____________________________________________________
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
				Problem problem = createProblemBuilder(status, problemType, detail)
						.userMessage(detail)
						.timestamp(LocalDateTime.now())
						.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(),status, request);
		
	}
	
	//4______________________________________________________
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}else if(rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "Erro de sintaxe na formulação da requisição";
		
			Problem problem = createProblemBuilder(status, problemType, detail)
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
					.timestamp(LocalDateTime.now())
					.build();
			
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
			//4.1________________________
			private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, 
					HttpStatus status, WebRequest request){
				
				ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
				String path = joinPath(ex.getPath());
						
				String detail = String.format("A propriedade '%s' recebeu o valor '%s'"
						+ ", que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'"
						, path, ex.getValue(), ex.getTargetType().getSimpleName());
				
				Problem problem = createProblemBuilder(status, problemType, detail)
						.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
						.build();
						
				return handleExceptionInternal(ex, problem, headers, status, request);
			}
			
			//4.2________________
			
			private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, 
					HttpStatus status, WebRequest request) {
				
				ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
				String path = joinPath(ex.getPath());
				String detail = String.format("A propriedade '%s' não existe. "
						+ "Corrija ou remova esta propriedade e tente novmente", path);
				Problem problem = createProblemBuilder(status, problemType,detail)
						.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
						.build();
				
				return handleExceptionInternal(ex, problem, headers, status, request);
				
			}
	
				//4.3________________
				private String joinPath(List<Reference> references) {
					return references.stream().map(ref -> ref.getFieldName())
							.collect(Collectors.joining("."));
				}
	//5_____________________________
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(org.springframework.beans.TypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		return super.handleTypeMismatch(ex, headers, status, request);
	}
				
	//5.1_____________________________			
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request){
						
			ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
			String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
					+ "que é de um tipo inválido. Corrija e informe um valor compatível "
					+ "com o tipo %s.", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
			
			Problem problem = createProblemBuilder(status, problemType, detail)
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
					.timestamp(LocalDateTime.now())
					.build();	
			
			
		return handleExceptionInternal(ex, problem, headers, status, request);				
	}
	
	
	//6________________
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request){
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DO_SISTEMA;
		String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		ex.printStackTrace();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	//7_____________________________________
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos inválidos. Faça o preenchimento correto e tente novamente";
		
		List<Problem.Field> problemFields = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> Problem.Field.builder()
						.name(fieldError.getField())
						.userMessage(fieldError.getDefaultMessage())
						.build()) 
				.collect(Collectors.toList());
				
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.fields(problemFields)
				.build();
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	

	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body == null) {
			body = Problem.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.timestamp(LocalDateTime.now())
				.build();
		}else if(body instanceof String) {
			body = Problem.builder()
				.title((String)body)
				.status(status.value())
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.timestamp(LocalDateTime.now())
				.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail)
				.timestamp(LocalDateTime.now());
		
	}
	
	
	
}
