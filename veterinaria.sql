-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 19-11-2022 a las 12:00:06
-- Versión del servidor: 8.0.28
-- Versión de PHP: 8.0.16

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
('111111111', 'cliente1', 'apellido1 apellido1', 'Cliente 1 direccion', 'primercliente@gmail.com', '111-11-11-11'),
('222222222', 'cliente2', 'apellido2 apellido2', 'Cliente 2 direccion', 'segundocliente@gmail.com', '222-22-22-22'),
('444444444', 'cliente4', 'apellido4 apellido4', 'Cliente 4 direccion', 'cuartocliente@gmail.com', '444-44-44-44'),
('555555555', 'cliente5Nuevo', 'apellido5Nuevo apellido5Nuevo', 'Cliente 5 nueva direccion', 'quintoclienteNuevo@gmail.com', '555-55-66-55'),
('777777777', 'cliente7', 'apellido7 apellido5', 'Cliente 7 direccion', 'septimocliente@gmail.com', '777-77-77-77');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta_veterinario`
--

CREATE TABLE `cuenta_veterinario` (
  `correo` varchar(100) NOT NULL,
  `contrasenia` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo_intervencion`
--

CREATE TABLE `equipo_intervencion` (
  `id_intervencion` int NOT NULL,
  `dni_veterinario` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad_veterinario`
--

CREATE TABLE `especialidad_veterinario` (
  `id` int NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especie_mascota`
--

CREATE TABLE `especie_mascota` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `peligrosa` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `id_factura` int NOT NULL,
  `id_equipo_intervencion` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(2, 'Cirugía'),
(3, 'Consulta'),
(5, 'Nuevo tipo'),
(1, 'Revision');

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
  ADD UNIQUE KEY `dni_veterinario` (`dni_veterinario`),
  ADD KEY `equipo_intervencion_ibfk_1` (`id_intervencion`);

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
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `id_equipo_intervencion` (`id_equipo_intervencion`),
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
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `especie_mascota`
--
ALTER TABLE `especie_mascota`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `intervencion`
--
ALTER TABLE `intervencion`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_intervencion`
--
ALTER TABLE `tipo_intervencion`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
