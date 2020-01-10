SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `code` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Room_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `size` enum('SINGLE','DOUBLE') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`code`) USING BTREE,
  INDEX `fk_Bed_Room_idx`(`Room_number`) USING BTREE,
  CONSTRAINT `fk_Bed_Room` FOREIGN KEY (`Room_number`) REFERENCES `room` (`number`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bookedroom
-- ----------------------------
DROP TABLE IF EXISTS `bookedroom`;
CREATE TABLE `bookedroom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Booking_reference` int(11) NOT NULL,
  `Room_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_BookedRoom_Booking1_idx`(`Booking_reference`) USING BTREE,
  INDEX `fk_BookedRoom_Room1_idx`(`Room_number`) USING BTREE,
  CONSTRAINT `fk_BookedRoom_Booking1` FOREIGN KEY (`Booking_reference`) REFERENCES `booking` (`reference`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_BookedRoom_Room1` FOREIGN KEY (`Room_number`) REFERENCES `room` (`number`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking`  (
  `reference` int(11) NOT NULL AUTO_INCREMENT,
  `movein` date NOT NULL,
  `moveout` date NOT NULL,
  PRIMARY KEY (`reference`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ssn` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `surname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customerorder
-- ----------------------------
DROP TABLE IF EXISTS `customerorder`;
CREATE TABLE `customerorder`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_id` int(11) NOT NULL,
  `Booking_reference` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_Order_Customer1_idx`(`Customer_id`) USING BTREE,
  INDEX `fk_Order_Booking1_idx`(`Booking_reference`) USING BTREE,
  CONSTRAINT `fk_Order_Booking1` FOREIGN KEY (`Booking_reference`) REFERENCES `booking` (`reference`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Customer1` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `floor` smallint(3) NOT NULL,
  `class` enum('ECONOMY','MIDDLE','LUXURY') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
