-- Crear base de datos
CREATE DATABASE IF NOT EXISTS M07_incidencias;
USE M07_incidencias;

-- Tabla de empleados
CREATE TABLE Empleados (
    id_empleado INT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    nombre_completo VARCHAR(100) NOT NULL,
    telefono_contacto VARCHAR(15)
);

-- Tabla de incidencias
CREATE TABLE Incidencias (
    id_incidencia INT(11) PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_empleado_origen INT NOT NULL,
    id_empleado_destino INT NOT NULL,
    descripcion TEXT NOT NULL,
    estado CHAR(1) NOT NULL, -- 'U' (Urgente), 'N' (Normal), etc.
    FOREIGN KEY (id_empleado_origen) REFERENCES Empleados(id_empleado),
    FOREIGN KEY (id_empleado_destino) REFERENCES Empleados(id_empleado)
);

INSERT INTO Empleados VALUES
(1, 'e', 'e', 'e e', '654321987'),
(2, 'maria_gomez', 'maria2024', 'María Gómez', '687456123'),
(3, 'carlos_lopez', 'carlitos99', 'Carlos López', '698745632'),
(4, 'ana_rodriguez', 'anaR_85', 'Ana Rodríguez', '612345678'),
(5, 'pedro_martin', 'pedroM2023', 'Pedro Martín', '623987456'),
(6, 'laura_sanchez', 'lauSanx12', 'Laura Sánchez', '635214789'),
(7, 'david_fernandez', 'davidF_90', 'David Fernández', '647852369'),
(8, 'sandra_moreno', 'sanMo2022', 'Sandra Moreno', '659874123'),
(9, 'jose_ramirez', 'ramirezJ0s3', 'José Ramírez', '678912345'),
(10, 'elena_torres', 'elenaTorres!', 'Elena Torres', '689745231');

INSERT INTO Incidencias VALUES
(1,  '2025-03-06 15:32:07', 1, 2,  'Error en el sistema de login',                          'U'),
(2,  '2025-03-06 15:32:07', 1, 2,  'Error en el sistema de login',                          'U'),
(3,  '2025-03-06 15:32:07', 3, 2,  'Solicitud de acceso a la red interna',                  'N'),
(4,  '2025-03-06 15:32:07', 5, 8,  'Fallo en la impresora del departamento',                'U'),
(5,  '2025-03-06 15:32:07', 7, 8,  'Revisión del servidor de correo',                       'N'),
(6,  '2025-03-06 15:32:07', 2, 10, 'Problema con la VPN',                                   'U'),
(7,  '2025-03-06 15:32:07', 2, 3,  'Solicitud de actualización de software',                'N'),
(8,  '2025-03-06 15:32:07', 3, 5,  'Revisión del hardware en la oficina',                   'U'),
(9,  '2025-03-06 15:32:07', 7, 8,  'Configuración de permisos en carpetas compartidas',    'N'),
(10, '2025-03-06 15:32:07', 8, 10, 'Reparación de equipo informático',                      'U'),
(11, '2025-03-06 15:32:07', 10, 1, 'Problema con conexión a internet',                      'N'),
(12, '2025-03-06 15:32:07', 1, 3,  'Cambio de contraseña no autorizado',                    'U'),
(13, '2025-03-06 15:32:07', 2, 5,  'Error en aplicación de nómina',                         'N'),
(14, '2025-03-06 15:32:07', 5, 7,  'Restauración de copia de seguridad',                    'U'),
(15, '2025-03-06 15:32:07', 8, 7,  'Solicitud de soporte para nueva herramienta',           'N'),
(16, '2025-03-06 15:32:07', 8, 10, 'Problema con licencias de software',                    'U'),
(17, '2025-03-06 15:32:07', 3, 5,  'Acceso denegado en sistema de gestión',                 'N'),
(18, '2025-03-06 15:32:07', 7, 8,  'Configuración de firma digital',                        'U'),
(19, '2025-03-06 15:32:07', 3, 10, 'Revisión de logs de seguridad',                         'N'),
(20, '2025-03-06 15:32:07', 2, 7,  'Fallo en el acceso a la base de datos',                 'U');