-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 23-11-2022 a las 18:13:50
-- Versión del servidor: 8.0.31-0ubuntu0.22.04.1
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `veterinaria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `dni` char(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`dni`, `nombre`, `apellidos`, `direccion`, `correo`, `telefono`) VALUES
('12345678B', 'Tercer Cliente', 'Apellido3', NULL, 'tercercorreo@gmail.com', '933555444'),
('12345678G', 'Quinto Cliente', 'Apellido5', 'C/Del cliente quinto', 'segundocorreo@gmail.com', '99999993'),
('12345678Z', 'Primer Cliente', 'Apellido1', 'Primera dirección del cliente', 'primercorreo@gmail.com', '911222333'),
('87456123A', 'Segundo Cliente', 'Apellido2', 'Segunda dirección del cliente', NULL, '922333444'),
('87456123C', 'Cuarto Cliente', 'Apellido4', NULL, NULL, '900111222');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta_veterinario`
--

CREATE TABLE `cuenta_veterinario` (
  `correo` varchar(100) NOT NULL,
  `contrasenia` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cuenta_veterinario`
--

INSERT INTO `cuenta_veterinario` (`correo`, `contrasenia`) VALUES
('cuartocorreo@gmail.com', '4r3e2w1q'),
('primercorreo@gmail.com', '1234'),
('quintocorreo@gmail.com', 'PR0GR4M4'),
('segundocorreo@gmail.com', '1q2w3e4r'),
('tercercorreo@gmail.com', '4321');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo_intervencion`
--

CREATE TABLE `equipo_intervencion` (
  `id_intervencion` int NOT NULL,
  `dni_veterinario` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `equipo_intervencion`
--

INSERT INTO `equipo_intervencion` (`id_intervencion`, `dni_veterinario`) VALUES
(1, '12345678A'),
(4, '12345678A'),
(4, '12345678B'),
(2, '87654321Y'),
(3, '87654321Y');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad_veterinario`
--

CREATE TABLE `especialidad_veterinario` (
  `id` int NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `especialidad_veterinario`
--

INSERT INTO `especialidad_veterinario` (`id`, `nombre`) VALUES
(1, 'Cirujano'),
(4, 'Doctor'),
(3, 'Enfermero'),
(2, 'Pediatra');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especie_mascota`
--

CREATE TABLE `especie_mascota` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `peligrosa` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `especie_mascota`
--

INSERT INTO `especie_mascota` (`id`, `nombre`, `peligrosa`) VALUES
(1, 'Perro', 0),
(2, 'Gato', 0),
(3, 'Tigre', 1),
(4, 'Oso', 1),
(5, 'Hamster', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int NOT NULL,
  `fecha` timestamp NOT NULL,
  `coste` decimal(6,2) DEFAULT NULL,
  `detalles` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `fecha`, `coste`, `detalles`) VALUES
(1, '2022-11-24 00:09:44', '13.90', 'Se cortó las uñas'),
(2, '2022-11-24 00:09:44', '25.87', 'Se cortó el pelo'),
(3, '2022-11-24 00:09:44', '10.00', 'Consulta periódica'),
(4, '2022-11-24 00:09:44', '224.90', 'Se operó la pata');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intervencion`
--

CREATE TABLE `intervencion` (
  `id` int NOT NULL,
  `asunto` varchar(30) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `id_tipo_intervencion` int NOT NULL,
  `id_mascota` int NOT NULL,
  `id_factura` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `intervencion`
--

INSERT INTO `intervencion` (`id`, `asunto`, `descripcion`, `id_tipo_intervencion`, `id_mascota`, `id_factura`) VALUES
(1, 'Asunto1', 'Descripción blabla', 1, 1, 1),
(2, 'Asunto2', 'Descripción hehehe', 2, 3, 2),
(3, 'Asunto3', 'Descripción taratara', 3, 2, 3),
(4, 'Asunto4', 'Descripción bipbopbip', 4, 4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota`
--

CREATE TABLE `mascota` (
  `id` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `fecha_nacimiento` timestamp NOT NULL,
  `peso` decimal(6,2) DEFAULT NULL,
  `dni_cliente` char(9) NOT NULL,
  `id_especie` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `mascota`
--

INSERT INTO `mascota` (`id`, `nombre`, `fecha_nacimiento`, `peso`, `dni_cliente`, `id_especie`) VALUES
(1, 'MascotaPrimera', '2022-11-24 00:09:44', '2.91', '12345678Z', 1),
(2, 'MascotaSegunda', '2022-11-24 00:09:44', '200.91', '12345678Z', 2),
(3, 'MascotaTercera', '2022-11-24 00:09:44', '13.23', '12345678B', 3),
(4, 'MascotaCuarta', '2022-11-24 00:09:44', '0.12', '12345678Z', 5),
(5, 'MascotaQuinta', '2022-11-24 00:09:44', '245.91', '12345678G', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id` int NOT NULL,
  `fecha_inicio` timestamp NOT NULL,
  `fecha_fin` timestamp NOT NULL,
  `id_intervencion` int DEFAULT NULL,
  `id_restriccion_dia` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `fecha_inicio`, `fecha_fin`, `id_intervencion`, `id_restriccion_dia`) VALUES
(1, '2022-12-01 00:00:00', '2022-12-01 00:00:00', 1, 'laboral'),
(2, '2022-12-02 00:00:00', '2022-12-02 00:00:00', 1, 'laboral'),
(3, '2022-12-03 00:00:00', '2022-12-03 00:00:00', 1, 'laboral'),
(4, '2022-12-04 00:00:00', '2022-12-04 00:00:00', 1, 'laboral'),
(5, '2022-12-04 09:00:00', '2022-12-04 10:00:00', 1, 'laboral');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_intervencion`
--

CREATE TABLE `tipo_intervencion` (
  `id` int NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipo_intervencion`
--

INSERT INTO `tipo_intervencion` (`id`, `tipo`) VALUES
(1, 'Cirugía'),
(2, 'Consulta'),
(3, 'Estética'),
(4, 'Pre-operatorio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_restriccion_dia`
--

CREATE TABLE `tipo_restriccion_dia` (
  `tipo` varchar(20) NOT NULL,
  `hora_apertura` time NOT NULL,
  `hora_cierre` time NOT NULL,
  `intervalo_tiempo` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipo_restriccion_dia`
--

INSERT INTO `tipo_restriccion_dia` (`tipo`, `hora_apertura`, `hora_cierre`, `intervalo_tiempo`) VALUES
('laboral', '08:00:00', '15:00:00', 60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `veterinario`
--

CREATE TABLE `veterinario` (
  `dni` char(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `cuenta_veterinaria` varchar(100) NOT NULL,
  `id_especialidad` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `veterinario`
--

INSERT INTO `veterinario` (`dni`, `nombre`, `apellidos`, `telefono`, `cuenta_veterinaria`, `id_especialidad`) VALUES
('12345678A', 'PrimerVeterinario', 'PrimerApellido PrimerApellido', '922122345', 'primercorreo@gmail.com', 1),
('12345678B', 'TercerVeterinario', 'TercerApellido TercerApellido', '933122345', 'tercercorreo@gmail.com', 3),
('12345678C', 'Quinto Veterinario', 'QuintoApellido QuintoApellido', '955122345', 'quintocorreo@gmail.com', 1),
('87654321Y', 'CuartoVeterinario', 'CuartoApellido CuartoApellido', '944122345', 'cuartocorreo@gmail.com', 4),
('87654321Z', 'SegundoVeterinario', 'SegundoApellido SegundoApellido', '911122345', 'segundocorreo@gmail.com', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `cuenta_veterinario`
--
ALTER TABLE `cuenta_veterinario`
  ADD PRIMARY KEY (`correo`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `equipo_intervencion`
--
ALTER TABLE `equipo_intervencion`
  ADD PRIMARY KEY (`id_intervencion`,`dni_veterinario`),
  ADD KEY `equipo_intervencion_ibfk_2` (`dni_veterinario`);

--
-- Indices de la tabla `especialidad_veterinario`
--
ALTER TABLE `especialidad_veterinario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `especie_mascota`
--
ALTER TABLE `especie_mascota`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `intervencion`
--
ALTER TABLE `intervencion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_tipo_intervencion` (`id_tipo_intervencion`),
  ADD KEY `id_factura` (`id_factura`),
  ADD KEY `id_mascota` (`id_mascota`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dni_cliente` (`dni_cliente`),
  ADD KEY `id_especie` (`id_especie`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `reserva_ibfk_1` (`id_intervencion`),
  ADD KEY `reserva_ibfk_2` (`id_restriccion_dia`);

--
-- Indices de la tabla `tipo_intervencion`
--
ALTER TABLE `tipo_intervencion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tipo` (`tipo`);

--
-- Indices de la tabla `tipo_restriccion_dia`
--
ALTER TABLE `tipo_restriccion_dia`
  ADD PRIMARY KEY (`tipo`);

--
-- Indices de la tabla `veterinario`
--
ALTER TABLE `veterinario`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD KEY `cuenta_veterinaria` (`cuenta_veterinaria`),
  ADD KEY `id_especialidad` (`id_especialidad`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `especialidad_veterinario`
--
ALTER TABLE `especialidad_veterinario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `especie_mascota`
--
ALTER TABLE `especie_mascota`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `intervencion`
--
ALTER TABLE `intervencion`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_intervencion`
--
ALTER TABLE `tipo_intervencion`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo_intervencion`
--
ALTER TABLE `equipo_intervencion`
  ADD CONSTRAINT `equipo_intervencion_ibfk_1` FOREIGN KEY (`id_intervencion`) REFERENCES `intervencion` (`id`),
  ADD CONSTRAINT `equipo_intervencion_ibfk_2` FOREIGN KEY (`dni_veterinario`) REFERENCES `veterinario` (`dni`);

--
-- Filtros para la tabla `intervencion`
--
ALTER TABLE `intervencion`
  ADD CONSTRAINT `intervencion_ibfk_1` FOREIGN KEY (`id_tipo_intervencion`) REFERENCES `tipo_intervencion` (`id`),
  ADD CONSTRAINT `intervencion_ibfk_2` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id`),
  ADD CONSTRAINT `intervencion_ibfk_4` FOREIGN KEY (`id_mascota`) REFERENCES `mascota` (`id`);

--
-- Filtros para la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD CONSTRAINT `mascota_ibfk_1` FOREIGN KEY (`dni_cliente`) REFERENCES `cliente` (`dni`),
  ADD CONSTRAINT `mascota_ibfk_2` FOREIGN KEY (`id_especie`) REFERENCES `especie_mascota` (`id`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_intervencion`) REFERENCES `intervencion` (`id`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_restriccion_dia`) REFERENCES `tipo_restriccion_dia` (`tipo`);

--
-- Filtros para la tabla `veterinario`
--
ALTER TABLE `veterinario`
  ADD CONSTRAINT `veterinario_ibfk_1` FOREIGN KEY (`cuenta_veterinaria`) REFERENCES `cuenta_veterinario` (`correo`),
  ADD CONSTRAINT `veterinario_ibfk_2` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad_veterinario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
