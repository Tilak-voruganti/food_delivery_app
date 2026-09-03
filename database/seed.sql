CREATE DATABASE IF NOT EXISTS food CHARACTER SET utf8mb4;
USE food;

CREATE TABLE IF NOT EXISTS `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `phonenumber` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastlogindate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurantid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(20) DEFAULT NULL,
  `cusinetype` varchar(100) DEFAULT NULL,
  `deliverytime` varchar(50) DEFAULT NULL,
  `admineuserid` int DEFAULT NULL,
  `rating` varchar(20) DEFAULT NULL,
  `isactive` varchar(20) DEFAULT NULL,
  `imagepath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`restaurantid`),
  CONSTRAINT `restaurant_user_fk` FOREIGN KEY (`admineuserid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `menu` (
  `menuid` int NOT NULL AUTO_INCREMENT,
  `restaurantid` int DEFAULT NULL,
  `itemname` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `isavailable` varchar(20) DEFAULT NULL,
  `ratings` float DEFAULT NULL,
  `imagepath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menuid`),
  CONSTRAINT `menu_restaurant_fk` FOREIGN KEY (`restaurantid`) REFERENCES `restaurant` (`restaurantid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `order` (
  `orderid` int NOT NULL AUTO_INCREMENT,
  `restaurantid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `orderdate` timestamp NULL DEFAULT NULL,
  `totalamount` int DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `paymentmode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  CONSTRAINT `order_restaurant_fk` FOREIGN KEY (`restaurantid`) REFERENCES `restaurant` (`restaurantid`),
  CONSTRAINT `order_user_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `order_item` (
  `orderitemid` int NOT NULL AUTO_INCREMENT,
  `orderid` int DEFAULT NULL,
  `menuid` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `totalamount` int DEFAULT NULL,
  PRIMARY KEY (`orderitemid`),
  CONSTRAINT `order_item_order_fk` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderid`),
  CONSTRAINT `order_item_menu_fk` FOREIGN KEY (`menuid`) REFERENCES `menu` (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO restaurant (restaurantid, name, address, phonenumber, cusinetype, deliverytime, admineuserid, rating, isactive, imagepath) VALUES
(1, 'Spice Garden', '12 MG Road', '9876543210', 'Indian', '30-40 mins', NULL, '4.6', 'active', 'https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?auto=format&fit=crop&w=900&q=80'),
(2, 'Urban Tandoor', '45 Park Street', '9876543211', 'North Indian', '25-35 mins', NULL, '4.4', 'active', 'https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?auto=format&fit=crop&w=900&q=80'),
(3, 'Green Bowl Cafe', '8 Lake View Road', '9876543212', 'Healthy Food', '20-30 mins', NULL, '4.7', 'active', 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=900&q=80'),
(4, 'Pizza Piazza', '21 Central Avenue', '9876543213', 'Italian', '35-45 mins', NULL, '4.5', 'active', 'https://images.unsplash.com/photo-1579751626657-72bc17010498?auto=format&fit=crop&w=900&q=80'),
(5, 'Burger Foundry', '17 Riverside Lane', '9876543214', 'American', '25-35 mins', NULL, '4.3', 'active', 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=900&q=80'),
(6, 'Sushi Harbor', '9 Marina Road', '9876543215', 'Japanese', '30-40 mins', NULL, '4.8', 'active', 'https://images.unsplash.com/photo-1579871494447-9811cf80d66c?auto=format&fit=crop&w=900&q=80'),
(7, 'The Breakfast Club', '3 Sunrise Street', '9876543216', 'Breakfast', '15-25 mins', NULL, '4.5', 'active', 'https://images.unsplash.com/photo-1533089860892-a7c6f0a88666?auto=format&fit=crop&w=900&q=80'),
(8, 'Sweet Truth Bakery', '66 Market Square', '9876543217', 'Desserts', '20-30 mins', NULL, '4.6', 'active', 'https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=900&q=85');

INSERT INTO menu (restaurantid, itemname, description, price, isavailable, ratings, imagepath) VALUES
(1, 'Paneer Tikka', 'Char-grilled cottage cheese with peppers and house spices.', 220, 'available', 4.7, 'https://images.unsplash.com/photo-1567188040759-fb8a883dc6d8?auto=format&fit=crop&w=800&q=80'),
(1, 'Butter Chicken', 'Tender chicken in a creamy tomato and butter sauce.', 320, 'available', 4.8, 'https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?auto=format&fit=crop&w=800&q=80'),
(1, 'Garlic Naan', 'Soft tandoor naan finished with garlic and butter.', 90, 'available', 4.6, 'https://images.unsplash.com/photo-1601050690597-df0568f70950?auto=format&fit=crop&w=800&q=80'),
(2, 'Chicken Biryani', 'Fragrant basmati rice layered with spiced chicken.', 280, 'available', 4.8, 'https://images.unsplash.com/photo-1563379091339-03246963d51a?auto=format&fit=crop&w=800&q=80'),
(2, 'Dal Makhani', 'Slow-cooked black lentils with cream and aromatic spices.', 190, 'available', 4.5, 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?auto=format&fit=crop&w=800&q=80'),
(2, 'Tandoori Prawns', 'Juicy prawns marinated in yogurt and tandoori masala.', 360, 'available', 4.7, 'https://images.unsplash.com/photo-1559339352-11d035aa65de?auto=format&fit=crop&w=800&q=80'),
(3, 'Avocado Power Bowl', 'Avocado, quinoa, greens, corn and roasted vegetables.', 260, 'available', 4.8, 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=800&q=80'),
(3, 'Falafel Salad', 'Crisp falafel with greens, hummus and lemon dressing.', 210, 'available', 4.6, 'https://images.unsplash.com/photo-1540420773420-3366772f4999?auto=format&fit=crop&w=800&q=80'),
(3, 'Berry Smoothie', 'Mixed berries blended with banana and Greek yogurt.', 160, 'available', 4.5, 'https://images.unsplash.com/photo-1553530666-ba11a7da3888?auto=format&fit=crop&w=800&q=80'),
(4, 'Margherita Pizza', 'Hand-stretched pizza with tomato, mozzarella and basil.', 299, 'available', 4.7, 'https://images.unsplash.com/photo-1579751626657-72bc17010498?auto=format&fit=crop&w=800&q=80'),
(4, 'Pepperoni Pizza', 'Classic pizza topped with pepperoni and melted cheese.', 399, 'available', 4.8, 'https://images.unsplash.com/photo-1628840042765-356cda07504e?auto=format&fit=crop&w=800&q=80'),
(4, 'Penne Arrabbiata', 'Penne pasta tossed in a spicy tomato and garlic sauce.', 240, 'available', 4.4, 'https://images.unsplash.com/photo-1473093295043-cdd812d0e601?auto=format&fit=crop&w=800&q=80'),
(5, 'Classic Smash Burger', 'Crispy-edged beef patty, cheddar, lettuce and secret sauce.', 299, 'available', 4.6, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=800&q=80'),
(5, 'Crispy Chicken Burger', 'Crunchy chicken fillet with slaw and smoky mayo.', 279, 'available', 4.5, 'https://images.unsplash.com/photo-1606755962773-d324e0a13086?auto=format&fit=crop&w=800&q=80'),
(5, 'Loaded Fries', 'Golden fries topped with cheese sauce and jalapenos.', 149, 'available', 4.3, 'https://images.unsplash.com/photo-1573080496219-bb080dd4f877?auto=format&fit=crop&w=800&q=80'),
(6, 'Salmon Sushi Roll', 'Fresh salmon, avocado and cucumber rolled in seasoned rice.', 420, 'available', 4.8, 'https://images.unsplash.com/photo-1579871494447-9811cf80d66c?auto=format&fit=crop&w=800&q=80'),
(6, 'Chicken Teriyaki Bowl', 'Grilled chicken, steamed rice and vegetables with teriyaki glaze.', 340, 'available', 4.6, 'https://images.unsplash.com/photo-1547592180-85f173990554?auto=format&fit=crop&w=800&q=80'),
(6, 'Edamame', 'Steamed soybeans finished with sea salt.', 120, 'available', 4.4, 'https://images.unsplash.com/photo-1564894809611-1742fc40ed80?auto=format&fit=crop&w=800&q=80'),
(7, 'Pancake Stack', 'Fluffy pancakes with maple syrup, berries and butter.', 220, 'available', 4.7, 'https://images.unsplash.com/photo-1528207776546-365bb710ee93?auto=format&fit=crop&w=800&q=80'),
(7, 'Masala Omelette', 'Three-egg omelette with onion, tomato, chilli and herbs.', 150, 'available', 4.5, 'https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=800&q=80'),
(7, 'Cold Coffee', 'Chilled coffee blended with milk and a touch of vanilla.', 130, 'available', 4.6, 'https://images.unsplash.com/photo-1461023058943-07fcbe16d735?auto=format&fit=crop&w=800&q=80'),
(8, 'Chocolate Cake', 'Rich chocolate sponge layered with silky ganache.', 180, 'available', 4.8, 'https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=800&q=80'),
(8, 'Strawberry Tart', 'Buttery pastry filled with cream and fresh strawberries.', 160, 'available', 4.6, 'https://images.unsplash.com/photo-1464305795204-6f5bbfc7fb81?auto=format&fit=crop&w=800&q=80'),
(8, 'Cinnamon Roll', 'Soft baked roll with cinnamon sugar and vanilla glaze.', 110, 'available', 4.7, 'https://images.unsplash.com/photo-1509365465985-25d11c17e812?auto=format&fit=crop&w=800&q=80');
