CREATE TABLE `entidadbancaria` (
	`idEntidadBancaria` INT(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(50) NULL DEFAULT NULL,
	`CodigoEntidad` VARCHAR(50) NULL DEFAULT NULL,
	`FechaCreacion` DATE NULL DEFAULT NULL,
	`Direccion` VARCHAR(50) NULL DEFAULT NULL,
	`CIF` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`idEntidadBancaria`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=52
;

INSERT INTO `entidadbancaria` (`idEntidadBancaria`, `Nombre`, `CodigoEntidad`, `FechaCreacion`, `Direccion`, `CIF`) VALUES
	(00000000013, 'sssss', '123', '2015-01-01', 'sss', 'sss'),
	(00000000015, 'ww', '123', '2015-01-01', 'ww', 'ww'),
	(00000000016, 'Banco', '333', '2015-10-01', 'av c', 'WERQ'),
	(00000000017, 'Sabadell', '011', '2015-10-01', 'av a', '4VFHYTa'),
	(00000000018, 'sabadell', '02342', '2015-10-01', 'avasdb', 'DWTG9'),
	(00000000019, 'Banco', '333', '2015-10-01', 'av c', 'WERQ'),
	(00000000020, 'kkk', 'asdasd', '2015-02-02', 'eweew', '312313'),
	(00000000021, 'dasd', 'dasd', '2015-02-02', 'asdasd', 'asdas'),
	(00000000022, 'Sabadell', '011', '2015-10-01', 'av a', '4VFHYTa'),
	(00000000023, 'sabadell', '02342', '2015-10-01', 'avasdb', 'DWTG9'),
	(00000000024, 'Banco', '333', '2015-10-01', 'av c', 'WERQ'),
	(00000000027, '3123123', '123', '2015-02-02', '1231', '313131'),
	(00000000028, '3123123', '123', '2015-02-02', '423rer', '313131'),
	(00000000029, 'dasdasda', '123', '2015-02-02', '423rer', '313131'),
	(00000000030, 'asdasd', 'weqwew', '2015-02-02', 'dasdas', 'asdasd'),
	(00000000032, 'Sabadell', '011', '2015-10-08', 'av a', '4VFHYTa'),
	(00000000033, 'Sabadell', '011', '2015-10-08', 'av a', '4VFHYTa'),
	(00000000034, 'sabadell', '02342', '2015-10-08', 'avasdb', 'DWTG9'),
	(00000000035, 'Banco', '333', '2015-10-08', 'av c', 'WERQ'),
	(00000000036, 'Sabadell', '011', '2015-10-14', 'av a', '4VFHYTa'),
	(00000000037, 'sabadell', '02342', '2015-10-14', 'avasdb', 'DWTG9'),
	(00000000042, 'autowire', '011', '2015-10-15', 'av a', '4VFHYTa'),
	(00000000048, '22', '2', '2015-01-01', '2', '2'),
	(00000000049, 'pruebanueva', '111', '2015-01-01', '1231', '1313'),
	(00000000050, 'sdasd', '123', '2015-01-01', 'weqweqw', 'qweqwe'),
	(00000000051, '3333', '333', '2015-01-01', '333', '333');