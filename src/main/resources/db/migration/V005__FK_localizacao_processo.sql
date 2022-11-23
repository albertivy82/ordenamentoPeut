ALTER TABLE `processo` ADD CONSTRAINT `FK_localizacao_processo` FOREIGN KEY (`localizacao`) REFERENCES `localizacao` (`id`);
