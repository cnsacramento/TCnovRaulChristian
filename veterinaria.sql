-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 15-11-2022 a las 05:33:02
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

CREATE TABLE `clientes` (
  `dni` char(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta_veterinaria`
--

CREATE TABLE `cuentas_veterinarios` (
  `correo` varchar(100) NOT NULL,
  `contrasenia` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo_intervencion`
--

CREATE TABLE `equipo_intervencion` (
  `id` int NOT NULL,
  `dni_veterinario` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidades_veterinario`
--

CREATE TABLE `especialidades_veterinario` (
  `id` int NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especies_mascota`
--

CREATE TABLE `especies_mascota` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `peligrosa` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id` int NOT NULL,
  `fecha` timestamp NOT NULL,
  `coste` decimal(10,0) NOT NULL,
  `detalles` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesiones`
--

CREATE TABLE `sesiones` (
  `id_jornada` int NOT NULL,
  `id_intervencion` int DEFAULT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intervenciones`
--

CREATE TABLE `intervenciones` (
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
-- Estructura de tabla para la tabla `jornadas`
--

CREATE TABLE `jornadas` (
  `id` int NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascotas`
--

CREATE TABLE `mascotas` (
  `id` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `fecha_nacimiento` timestamp NOT NULL,
  `peso` decimal(4,3) DEFAULT NULL,
  `dni_cliente` char(9) NOT NULL,
  `id_especie` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_intervencion`
--

CREATE TABLE `tipos_intervencion` (
  `id` int NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `veterinarios`
--

CREATE TABLE `veterinarios` (
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
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `cuentas_veterinario`
--
ALTER TABLE `cuentas_veterinarios`
  ADD PRIMARY KEY (`correo`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `equipo_intervencion`
--
ALTER TABLE `equipo_intervencion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni_veterinario` (`dni_veterinario`);

--
-- Indices de la tabla `especialidad_veterinario`
--
ALTER TABLE `especialidades_veterinario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `especies_mascota`
--
ALTER TABLE `especies_mascota`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sesiones`
--
ALTER TABLE `sesiones`
  ADD PRIMARY KEY (`id_jornada`,`id_intervencion`);

--
-- Indices de la tabla `intervencion`
--
ALTER TABLE `intervenciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_tipo_intervencion` (`id_tipo_intervencion`),
  ADD KEY `id_factura` (`id_factura`),
  ADD KEY `id_equipo_intervencion` (`id_equipo_intervencion`),
  ADD KEY `id_mascota` (`id_mascota`);

--
-- Indices de la tabla `jornada`
--
ALTER TABLE `jornadas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dni_cliente` (`dni_cliente`),
  ADD KEY `id_especie` (`id_especie`);

--
-- Indices de la tabla `tipo_intervencion`
--
ALTER TABLE `tipos_intervencion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tipo` (`tipo`);

--
-- Indices de la tabla `veterinario`
--
ALTER TABLE `veterinarios`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD KEY `cuenta_veterinaria` (`cuenta_veterinaria`),
  ADD KEY `id_especialidad` (`id_especialidad`);

--
-- AUTO_INCREMENT de las tablas volcadas
--
--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascotas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `especialidad_veterinario`
--
ALTER TABLE `especialidades_veterinario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `especies_mascota`
--
ALTER TABLE `especies_mascota`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `facturas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `intervencion`
--
ALTER TABLE `intervenciones`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_intervencion`
--
ALTER TABLE `tipos_intervencion`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo_intervencion`
--
ALTER TABLE `equipo_intervencion`
  ADD CONSTRAINT `equipo_intervencion_ibfk_1` FOREIGN KEY (`dni_veterinario`) REFERENCES `veterinarios` (`dni`);

--
-- Filtros para la tabla `sesiones`
--
ALTER TABLE `sesiones`
  ADD CONSTRAINT `sesiones_ibfk_1` FOREIGN KEY (`id_intervencion`) REFERENCES `intervenciones` (`id`),
  ADD CONSTRAINT `sesiones_ibfk_2` FOREIGN KEY (`id_jornada`) REFERENCES `jornadas` (`id`);

--
-- Filtros para la tabla `intervenciones`
--
ALTER TABLE `intervenciones`
  ADD CONSTRAINT `intervenciones_ibfk_1` FOREIGN KEY (`id_tipo_intervencion`) REFERENCES `tipos_intervencion` (`id`),
  ADD CONSTRAINT `intervenciones_ibfk_2` FOREIGN KEY (`id_factura`) REFERENCES `facturas` (`id`),
  ADD CONSTRAINT `intervenciones_ibfk_3` FOREIGN KEY (`id_equipo_intervencion`) REFERENCES `equipo_intervencion` (`id`),
  ADD CONSTRAINT `intervenciones_ibfk_4` FOREIGN KEY (`id_mascota`) REFERENCES `mascotas` (`id`);

--
-- Filtros para la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD CONSTRAINT `mascotas_ibfk_1` FOREIGN KEY (`dni_cliente`) REFERENCES `clientes` (`dni`),
  ADD CONSTRAINT `mascotas_ibfk_2` FOREIGN KEY (`id_especie`) REFERENCES `especies_mascota` (`id`);

--
-- Filtros para la tabla `veterinarios`
--
ALTER TABLE `veterinarios`
  ADD CONSTRAINT `veterinarios_ibfk_1` FOREIGN KEY (`cuenta_veterinaria`) REFERENCES `cuentas_veterinarios` (`correo`),
  ADD CONSTRAINT `veterinarios_ibfk_2` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades_veterinario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
