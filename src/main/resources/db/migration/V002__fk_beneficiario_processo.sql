ALTER TABLE `processo` ADD CONSTRAINT `fk_beneficiario_processo` FOREIGN KEY ( `beneficiarios` ) REFERENCES `beneficiario` ( `id` );