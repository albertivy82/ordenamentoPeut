ALTER TABLE `imagens` ADD CONSTRAINT `FK_processo_imagens` FOREIGN KEY (`processos`) REFERENCES `processo` (`id`);