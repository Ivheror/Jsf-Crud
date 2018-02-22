--
-- Estructura de tabla para la tabla `genero`
--
drop database if exists cine2;
create or replace database cine2;
use cine2;

CREATE TABLE `genero` (
  `id` int(2) NOT NULL,
  `descripcion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id`, `descripcion`) VALUES
(1, 'Terror'),
(2, 'Comedia'),
(3, 'Drama'),
(4, 'Gore'),
(5, 'Ciencia Ficcion'),
(6, 'Musical');

ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`);
  
   ALTER TABLE `genero` CHANGE `id` `id` INT( 2 ) NOT NULL AUTO_INCREMENT ;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `genero_personas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `genero_personas` (
`dni_persona` int(11)
,`nombre` varchar(50)
,`correo` varchar(20)
,`frecuente` char(1)
,`cod_peli` int(2)
,`id` int(2)
,`descripcion` varchar(20)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `dni_persona` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `correo` varchar(20) NOT NULL,
  `frecuente` char(1) NOT NULL,
  `cod_peli` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`dni_persona`, `nombre`, `correo`, `frecuente`, `cod_peli`) VALUES
(1, 'Pepe Flores', 'pflores@gmail.com', 'S', 1),
(2, 'Juana Navarro', 'navarraj@gmail.com', 'N', 6),
(3, 'Mike Litoris', 'mkli@gmail.com', 'S', 3),
(4, 'Dolores Fuertes Barriga', 'diarrea@gmail.com', 'S', 4),
(5, 'Cyndi Nero', 'pobre@gmail.com', 'N', 2),
(6, 'Jack Daniels', 'nobebo@gmail.com', 'N', 5),
(7, 'Carles Puigdemont', 'bibacatalunia@catalu', 'S', 3),
(8, 'Cristiano Ronaldo', 'siuuu@cr7.pt', 'S', 5),
(9, 'Rafael Nadal', 'vamoss@nadalatope.es', 'S', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `piscolabis`
--

CREATE TABLE `piscolabis` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `piscolabis`
--

INSERT INTO `piscolabis` (`id`, `descripcion`) VALUES
(1, 'Palomitas'),
(2, 'Sprite'),
(3, 'Cerveza'),
(4, 'Chucherias'),
(5, 'Coca-Cola');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `piscolabis_personas`
--

CREATE TABLE `piscolabis_personas` (
  `id` int(2) NOT NULL,
  `dni_persona` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `piscolabis_personas`
--

INSERT INTO `piscolabis_personas` (`id`, `dni_persona`) VALUES
(5, '2'),
(2, '3'),
(4, '4'),
(1, '5'),
(5, '6'),
(1, '6'),
(1, '7'),
(3, '7'),
(4, '7'),
(2, '8'),
(4, '8'),
(1, '1'),
(2, '1'),
(3, '1'),
(3, '9'),
(4, '9');

-- --------------------------------------------------------

--
-- Estructura para la vista `genero_personas`
--
DROP TABLE IF EXISTS `genero_personas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `genero_personas`  AS  select `p`.`dni_persona` AS `dni_persona`,`p`.`nombre` AS `nombre`,`p`.`correo` AS `correo`,`p`.`frecuente` AS `frecuente`,`p`.`cod_peli` AS `cod_peli`,`g`.`id` AS `id`,`g`.`descripcion` AS `descripcion` from (`personas` `p` join `genero` `g`) where (`p`.`cod_peli` = `g`.`id`) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `genero`
--


--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`dni_persona`),
  ADD KEY `FK_COD_PELI` (`cod_peli`);

--
-- Indices de la tabla `piscolabis`
--
ALTER TABLE `piscolabis`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `piscolabis_personas`
--
ALTER TABLE `piscolabis_personas`
  ADD KEY `FK_DNI` (`dni_persona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `dni_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `piscolabis`
--
ALTER TABLE `piscolabis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `personas`
--
ALTER TABLE `personas`
  ADD CONSTRAINT `personas_ibfk_1` FOREIGN KEY (`cod_peli`) REFERENCES `genero` (`id`);

