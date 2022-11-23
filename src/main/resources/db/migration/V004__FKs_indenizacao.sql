ALTER TABLE `indenizacao` ADD CONSTRAINT `FK_bancos_indenizado` FOREIGN KEY (`conta_deposito`) REFERENCES `dados_bancarios` (`id`);
ALTER TABLE `indenizacao` ADD CONSTRAINT `FK_processo_indenizado` FOREIGN KEY (`processos`) REFERENCES `processo` (`id`);
ALTER TABLE `indenizacao` ADD CONSTRAINT `FK_orcamento_indenizado` FOREIGN KEY (`orcamento`) REFERENCES `orcamento` (`id`);