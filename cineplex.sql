-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-09-16 16:50:28
-- 服务器版本： 5.6.24
-- PHP Version: 5.5.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cineplex`
--

-- --------------------------------------------------------

--
-- 表的结构 `answerform`
--

CREATE TABLE IF NOT EXISTS `answerform` (
  `id` int(11) NOT NULL,
  `phone` varchar(22) DEFAULT NULL,
  `qId` int(11) DEFAULT NULL,
  `selection` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `Id` int(11) NOT NULL,
  `userId` int(7) unsigned zerofill DEFAULT '0000000',
  `movieId` int(11) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`Id`, `userId`, `movieId`, `score`, `comment`) VALUES
(1, 0000031, 2, '5', '这是一部看起来很有趣的电影。《超能陆战队》的看点不只是其中的动作戏，更在于其中心角色的内心感情。（《华盛顿邮报》评）'),
(2, 0000030, 2, '4', '在构建了一个新鲜而又可靠的起源故事后，影片陷入了相对平庸的由反派而引发的困境。但本片依旧带给我们很多新鲜与不同的感觉，尤其是对那些尚不适合看紧张刺激的漫威真人动作片的观众。（《综艺》杂志评）\r\n'),
(3, 0000030, 2, '3', '影片显然是一部动画版的超能英雄大片，也再次印证迪士尼越来越成人化的路线。情节上有萌点也有笑点，每一幕都调动着观众的感情，或喜或悲，几个高潮点表现的都很精彩，节奏淋漓畅快，也有感人至深的煽情。（1905电影网评）');

-- --------------------------------------------------------

--
-- 表的结构 `hall`
--

CREATE TABLE IF NOT EXISTS `hall` (
  `movieId` int(11) DEFAULT NULL,
  `start` time DEFAULT NULL,
  `end` time DEFAULT NULL,
  `hallId` int(11) DEFAULT NULL,
  `Id` int(11) NOT NULL,
  `left_tickets` int(11) DEFAULT '51'
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `hall`
--

INSERT INTO `hall` (`movieId`, `start`, `end`, `hallId`, `Id`, `left_tickets`) VALUES
(2, '23:00:00', '23:59:00', 1, 35, 48),
(2, '10:00:00', '12:00:00', 1, 36, 45),
(2, '12:00:00', '14:00:00', 1, 37, 50),
(2, '14:00:00', '16:00:00', 1, 38, 51),
(2, '16:00:00', '18:30:00', 1, 39, 45),
(1, '12:00:00', '14:00:00', 2, 40, 49),
(1, '15:00:00', '17:00:00', 2, 41, 51),
(1, '00:00:00', '00:00:00', 2, 42, 51),
(2, '14:00:00', '16:00:00', 3, 43, 46);

-- --------------------------------------------------------

--
-- 表的结构 `halllist`
--

CREATE TABLE IF NOT EXISTS `halllist` (
  `hallId` int(11) NOT NULL COMMENT '不能为空'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `halllist`
--

INSERT INTO `halllist` (`hallId`) VALUES
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- 表的结构 `icbc`
--

CREATE TABLE IF NOT EXISTS `icbc` (
  `password` varchar(6) NOT NULL,
  `account` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `icbc`
--

INSERT INTO `icbc` (`password`, `account`) VALUES
('123', '6227');

-- --------------------------------------------------------

--
-- 表的结构 `manager`
--

CREATE TABLE IF NOT EXISTS `manager` (
  `Id` varchar(11) NOT NULL,
  `psw` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `manager`
--

INSERT INTO `manager` (`Id`, `psw`) VALUES
('admin', '123456');

-- --------------------------------------------------------

--
-- 表的结构 `movie`
--

CREATE TABLE IF NOT EXISTS `movie` (
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `country` varchar(255) CHARACTER SET utf8 NOT NULL,
  `company` varchar(255) CHARACTER SET utf8 NOT NULL,
  `price` double(4,2) NOT NULL DEFAULT '-1.00',
  `long_description` varchar(1000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `publish_date` date NOT NULL,
  `director` varchar(255) CHARACTER SET utf8 NOT NULL,
  `main_star` varchar(255) CHARACTER SET utf8 NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 NOT NULL,
  `short_description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `id` int(11) NOT NULL,
  `poster` varchar(255) DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `movie`
--

INSERT INTO `movie` (`name`, `country`, `company`, `price`, `long_description`, `publish_date`, `director`, `main_star`, `type`, `short_description`, `id`, `poster`) VALUES
('End Game', '美国 加拿大', 'end game productions', 35.00, '美国总统在好莱坞电影中要么像特种兵一样勇猛，要么就像瘪三一样惨遭暗杀，好似待宰的羔羊，全无还击之力。亚历克斯·托马斯做的是美国最“见不得光”的工作，他是总统身边的秘密特工，深得总统的信任，却只能活在总统的背后。然而，他的一个致命失策，却铸就了一个足以遗憾终身的错误：亚历克斯像往常一样出席公众活动的时候陪伴在总统左右，这时，眼尖的他发现人群中有一个人从怀里掏出手枪，一颗子弹应声而出，亚历克斯飞身救总统，但由于位置太远，已经来不及了……这时，意想不到的事情发生了，子弹打在亚历克斯的手掌上，竟然神奇地改变了行驶路线，最终射进了总统的胸膛。\r\n紧急手术的总统大人最终死在了手术台上--亚历克斯觉得一辈子都不可能原谅自己的这个重大失误，心灰意冷的他退出了特工部门，来到湖边的小屋闭门思过，整日用酒精麻痹着自己的神经。负责为这次暗杀事件做连续报道的记者凯特·克劳福德意外发现凶手竟然与某个秘密的政府机构有着暗线联系，当她决定深入调查这个蹊跷的发现时，能够为她提供线索的人却一个接一个被神秘的黑衣人暗杀，受到生命威胁的凯特找到亚历克斯的住处寻求帮助，却害得他的房子被炸得粉碎。亚历克斯终于相信，凯特所提供的一切并不是源于她丰富的想像力，这场谋杀案的背后，正酝酿着别一个惊天大阴谋。然而，就像影片描述的那样，发现阴谋和揭穿阴谋，完全是两种不同的概念。', '2009-12-25', 'Andy Cheng', '小古巴·古汀Cuba Gooding Jr. .....Alex Thomas\r\n詹姆斯·伍兹James Woods .....Vaughn Stevens\r\n安吉·哈蒙Angie Harmon .....Kate Crawford\r\n安妮·阿彻 Anne Archer .....The First Lady\r\n克里斯蒂娜·坎贝尔Christa Campbell .....Mistress\r\nJohnny C. .....Tony Morino\r\nHeather Dawn .....Reporter 1\r', '动作 / 惊悚 / 剧情', '由Andy Chen和著名影星小古巴·戈丁、安吉·哈蒙、詹姆斯·伍兹共同完成了他的第一部导演作品《终结游戏》。', 1, '"./images/end-game.jpg"'),
('超能陆战队', '美国', '华特迪士尼公司', 40.00, '《超能陆战队》（Big Hero 6）是迪士尼与漫威联合出品的第一部动画电影，取材于由Steven T. Seagle和Duncan Rouleau在1998年开始连载的以日本为背景的动作科幻类漫画。影片由唐·霍尔及克里斯·威廉姆斯联袂执导，瑞恩·波特、斯科特·埃德希特、T·J·米勒主演配音，于2014年11月7日以3D形式在北美上映。国内公映版于2015年2月28日在内地上映。《超能陆战队》主要讲述充气机器人大白与天才少年小宏联手菜鸟小伙伴组建超能战队，共同打击犯罪阴谋的故事。医护充气机器人大白更是将呆萌形象贯彻到底，成为全球影迷的心中最可爱的形象之一。这只超大号充气机器人实际上是个医疗伴侣：只要一次简单扫描，大白就能够检测出生命指数，并且根据病人的疼痛程度治疗几乎所有的疾病。对小宏来说，无微不至、天真无邪的大白是个英雄，更是最好的朋友。经过火箭拳套、超强力量等一系列精巧改造后，大白成为了"超能陆战队"的一员大将。', '2015-02-28', 'Dabai', '瑞恩·波特，斯科特·埃德希特，T·J·米勒，杰米·钟，小达蒙·威亚斯，珍妮塞丝·罗德里格兹，斯坦·李', '动画，动作，喜剧', '在一个融合东西方文化（旧金山+东京）的虚构大都市旧京山（San Fransokyo）中，一位精通机器人技术的小神童阿宏发现，这座充斥高新技术的城市正遭遇着一场犯罪危机，为了拆穿阴谋拯救家园，他将携伙伴机器人大白，与一伙生拉硬凑的菜鸟团队组成“超能陆战队”联盟，共同作战抗击罪恶。', 2, '"./posters/dabai.jpg"');

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `orderId` int(11) NOT NULL,
  `phone` varchar(22) CHARACTER SET utf8 DEFAULT NULL,
  `seatId` int(11) DEFAULT NULL,
  `periodId` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT 'online'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `orders`
--

INSERT INTO `orders` (`orderId`, `phone`, `seatId`, `periodId`, `date`, `type`) VALUES
(11, '1', 10, 39, '2015-04-02 14:41:46', 'cash'),
(12, '1', 9, 39, '2015-04-02 14:41:46', 'cash'),
(13, '121250089', 2, 35, '2015-04-13 18:47:10', 'online'),
(14, '121250089', 3, 35, '2015-04-13 18:47:10', 'online'),
(15, '121250089', 4, 35, '2015-04-13 18:47:10', 'online'),
(16, 'liu@qq.com', 3, 36, '2015-06-18 09:53:11', 'online'),
(17, 'liu@qq.com', 4, 36, '2015-06-18 09:53:11', 'online'),
(18, 'liu@qq.com', 10, 36, '2015-06-18 09:54:35', 'online'),
(19, 'liu@qq.com', 11, 36, '2015-06-18 09:55:29', 'online'),
(20, 'liu@qq.com', 18, 36, '2015-06-18 09:55:59', 'online'),
(21, 'liu39400078', 47, 39, '2015-06-22 11:07:39', 'online'),
(22, 'liu39400078', 46, 39, '2015-06-22 11:07:39', 'online'),
(23, 'lwt39400078', 35, 39, '2015-06-22 11:09:46', 'online'),
(24, 'lwt39400078', 36, 39, '2015-06-22 11:09:46', 'online'),
(25, 'liu39400078', 17, 40, '2015-06-23 10:46:05', 'online'),
(26, 'liu39400078', 18, 40, '2015-06-23 10:46:05', 'online'),
(27, 'liu39400078', 45, 43, '2015-06-23 13:00:39', 'online'),
(28, 'liu39400078', 46, 43, '2015-06-23 13:00:39', 'online'),
(29, 'liu39400078', 47, 43, '2015-06-23 13:00:39', 'online'),
(30, 'liu39400078', 35, 43, '2015-06-23 13:19:59', 'online'),
(31, 'liu39400078', 36, 43, '2015-06-23 13:19:59', 'online');

-- --------------------------------------------------------

--
-- 表的结构 `plans`
--

CREATE TABLE IF NOT EXISTS `plans` (
  `movieId` int(11) DEFAULT NULL,
  `start` time DEFAULT NULL,
  `end` time DEFAULT NULL,
  `waiterId` int(11) DEFAULT NULL,
  `hallId` int(11) DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT 'CHECKING'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `plans`
--

INSERT INTO `plans` (`movieId`, `start`, `end`, `waiterId`, `hallId`, `state`) VALUES
(2, '23:00:00', '23:59:00', 1, 1, 'ACCEPT'),
(2, '10:00:00', '12:00:00', 1, 1, 'ACCEPT'),
(2, '12:00:00', '14:00:00', 1, 1, 'ACCEPT'),
(2, '14:00:00', '16:00:00', 1, 1, 'ACCEPT'),
(2, '16:00:00', '18:30:00', 1, 1, 'ACCEPT'),
(1, '12:00:00', '14:00:00', 1, 2, 'ACCEPT'),
(1, '15:00:00', '17:00:00', 1, 2, 'ACCEPT'),
(1, '00:00:00', '00:00:00', 1, 2, 'ACCEPT'),
(2, '14:00:00', '16:00:00', 1, 3, 'ACCEPT'),
(1, '14:00:00', '16:00:00', 1, 1, 'REFUSED');

-- --------------------------------------------------------

--
-- 表的结构 `questionnaireform`
--

CREATE TABLE IF NOT EXISTS `questionnaireform` (
  `id` int(11) NOT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duedate` varchar(255) DEFAULT NULL,
  `movieId` varchar(255) DEFAULT NULL,
  `publishdate` varchar(255) DEFAULT NULL,
  `selection1` varchar(255) DEFAULT NULL,
  `selection2` varchar(255) DEFAULT NULL,
  `selection3` varchar(255) DEFAULT NULL,
  `waiterId` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT 'on'
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `questionnaireform`
--

INSERT INTO `questionnaireform` (`id`, `credit`, `description`, `duedate`, `movieId`, `publishdate`, `selection1`, `selection2`, `selection3`, `waiterId`, `state`) VALUES
(1, '5', '这部电影评多少分？', '2015-03-28', '2', '2015-03-28', '10', '5', '1', '2', 'off'),
(10, '5', 'test description', '2015-04-01', '1', '2015-03-29', 'yes', 'no', 'maybe', '2', 'on'),
(11, '5', 'test out date questions', '2015-03-01', '1', '2015-03-29', '11', '22', '33', '2', 'off'),
(12, '5', 'test on question', '2015-04-04', '1', '2015-03-29', '1', '2', '3', '2', 'on'),
(13, '5', '结算测试', '2015-03-03', '1', '2015-03-30', '10', '5', '1', '2', 'off'),
(14, '9', 'end test', '2015-03-03', '1', '2015-04-30', 'aa', 'bb', 'cc', '2', 'off'),
(15, '4', 'final end test', '2015-03-01', '1', '2015-03-30', 'AAA', 'BBB', 'CCC', '2', 'off'),
(16, '6', 'tes', '2015-04-01', '2', '2015-04-02', 'nice', 'bad', 'soso', '1', 'off');

-- --------------------------------------------------------

--
-- 表的结构 `rechargerecord`
--

CREATE TABLE IF NOT EXISTS `rechargerecord` (
  `id` int(11) NOT NULL,
  `phone` varchar(22) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `money` int(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `rechargerecord`
--

INSERT INTO `rechargerecord` (`id`, `phone`, `date`, `money`) VALUES
(1, '121250089', '2015-03-26 17:09:06', 88),
(2, '13225267900', '2015-04-02 14:33:58', 200),
(3, '121250089', '2015-04-13 18:47:44', 123),
(4, 'liu@qq.com', '2015-06-18 09:48:29', 111111),
(5, 'liu394000785@sina.com', '2015-06-22 11:00:38', 5000),
(6, 'lwt394000785@sina.com', '2015-06-22 11:09:29', 200);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `phone` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL,
  `VIP` int(1) NOT NULL DEFAULT '0',
  `expiration` date NOT NULL DEFAULT '2010-01-01',
  `ID` int(7) unsigned zerofill NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `age` int(11) NOT NULL DEFAULT '0',
  `gender` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '未设置',
  `city` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '未设置',
  `credit` int(11) DEFAULT '0',
  `activatestate` tinyint(1) NOT NULL DEFAULT '0',
  `activatecode` varchar(4) NOT NULL DEFAULT '0000'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`phone`, `password`, `VIP`, `expiration`, `ID`, `balance`, `age`, `gender`, `city`, `credit`, `activatestate`, `activatecode`) VALUES
('lwt394000785@sina.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '2016-06-22', 0000030, 120, 0, '未设置', '未设置', 0, 0, '6093'),
('liu394000785@sina.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '2016-06-22', 0000031, 4650, 0, '未设置', '未设置', 0, 1, '3527');

-- --------------------------------------------------------

--
-- 表的结构 `waiter`
--

CREATE TABLE IF NOT EXISTS `waiter` (
  `waiterId` int(11) NOT NULL,
  `waiterPsw` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `waiter`
--

INSERT INTO `waiter` (`waiterId`, `waiterPsw`) VALUES
(1, '123456'),
(2, '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answerform`
--
ALTER TABLE `answerform`
  ADD PRIMARY KEY (`id`), ADD KEY `phone` (`phone`), ADD KEY `qId` (`qId`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`Id`), ADD KEY `fk` (`userId`), ADD KEY `movieId` (`movieId`);

--
-- Indexes for table `hall`
--
ALTER TABLE `hall`
  ADD PRIMARY KEY (`Id`), ADD KEY `movidId` (`movieId`), ADD KEY `hallId` (`hallId`);

--
-- Indexes for table `halllist`
--
ALTER TABLE `halllist`
  ADD PRIMARY KEY (`hallId`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`), ADD KEY `periodId` (`periodId`), ADD KEY `phone` (`phone`);

--
-- Indexes for table `plans`
--
ALTER TABLE `plans`
  ADD KEY `fk1` (`waiterId`), ADD KEY `fk2` (`movieId`), ADD KEY `fk3` (`hallId`);

--
-- Indexes for table `questionnaireform`
--
ALTER TABLE `questionnaireform`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rechargerecord`
--
ALTER TABLE `rechargerecord`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`,`phone`), ADD KEY `phone` (`phone`);

--
-- Indexes for table `waiter`
--
ALTER TABLE `waiter`
  ADD PRIMARY KEY (`waiterId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answerform`
--
ALTER TABLE `answerform`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `hall`
--
ALTER TABLE `hall`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `questionnaireform`
--
ALTER TABLE `questionnaireform`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `rechargerecord`
--
ALTER TABLE `rechargerecord`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(7) unsigned zerofill NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `waiter`
--
ALTER TABLE `waiter`
  MODIFY `waiterId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- 限制导出的表
--

--
-- 限制表 `answerform`
--
ALTER TABLE `answerform`
ADD CONSTRAINT `answerform_ibfk_1` FOREIGN KEY (`phone`) REFERENCES `user` (`phone`),
ADD CONSTRAINT `answerform_ibfk_2` FOREIGN KEY (`qId`) REFERENCES `questionnaireform` (`id`);

--
-- 限制表 `comment`
--
ALTER TABLE `comment`
ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`ID`),
ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`id`),
ADD CONSTRAINT `fk` FOREIGN KEY (`userId`) REFERENCES `user` (`ID`);

--
-- 限制表 `hall`
--
ALTER TABLE `hall`
ADD CONSTRAINT `hallId` FOREIGN KEY (`hallId`) REFERENCES `halllist` (`hallId`);

--
-- 限制表 `orders`
--
ALTER TABLE `orders`
ADD CONSTRAINT `periodId` FOREIGN KEY (`periodId`) REFERENCES `hall` (`Id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- 限制表 `plans`
--
ALTER TABLE `plans`
ADD CONSTRAINT `fk1` FOREIGN KEY (`waiterId`) REFERENCES `waiter` (`waiterId`),
ADD CONSTRAINT `fk2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`id`),
ADD CONSTRAINT `fk3` FOREIGN KEY (`hallId`) REFERENCES `hall` (`hallId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
