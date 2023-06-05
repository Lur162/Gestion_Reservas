-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2023 a las 20:45:48
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reservas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id_comentario` bigint(20) NOT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `usuario_id_usuario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`id_comentario`, `fecha_publicacion`, `texto`, `usuario_id_usuario`) VALUES
(1, '2022-02-02', 'Todo bien', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id_reserva` bigint(20) NOT NULL,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `importe_total` double NOT NULL,
  `numero_personas` int(11) NOT NULL,
  `comentario_id_comentario` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`id_reserva`, `fecha_entrada`, `fecha_salida`, `importe_total`, `numero_personas`, `comentario_id_comentario`, `id_usuario`) VALUES
(1, '2022-02-02', '2022-02-07', 250, 8, 1, 1),
(47, '2023-05-27', '2023-05-30', 200, 1, NULL, 1),
(48, '2023-06-03', '2023-06-07', 250, 1, NULL, 1),
(49, '2023-06-10', '2023-06-13', 200, 3, NULL, 1),
(50, '2023-06-24', '2023-06-27', 200, 3, NULL, 1),
(51, '2023-07-08', '2023-07-11', 200, 1, NULL, 1),
(52, '2023-08-12', '2023-08-16', 250, 1, NULL, 1),
(53, '2023-07-22', '2023-07-26', 250, 1, NULL, 1),
(54, '2023-07-01', '2023-07-03', 150, 1, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` bigint(20) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `contrasenia` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `apellido`, `contrasenia`, `correo`, `nombre`) VALUES
(1, 'cano', '1234', 'lourdescanobarrero@gmail.com', 'lur'),
(5, 'Cano', '1234', 'lourdescano162@hotmail.com', 'Juan'),
(6, 'Antune', '1234', 'florian@gmail.com', 'Florian'),
(7, 'Cano', '1234', 'sergio@gmail.com', 'Sergio'),
(8, 'duran', '1234', 'raul@gmail.com', 'raul');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id_comentario`),
  ADD KEY `FKa9nogkc93crhva94w40tlfau9` (`usuario_id_usuario`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `FKhm9d8aujdjtdk06a4ionr6fvi` (`comentario_id_comentario`),
  ADD KEY `FKhjryje6u1cr0d4dubad1jja6` (`id_usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id_comentario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id_reserva` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `FKa9nogkc93crhva94w40tlfau9` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `FKhjryje6u1cr0d4dubad1jja6` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `FKhm9d8aujdjtdk06a4ionr6fvi` FOREIGN KEY (`comentario_id_comentario`) REFERENCES `comentarios` (`id_comentario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
