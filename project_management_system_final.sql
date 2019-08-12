-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2019 at 06:35 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_management_system_final`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_group_form` (IN `session_id` VARCHAR(255))  begin
	declare grp_form int default -1;
	declare resultset int;
	
	set resultset = (select grpid from student where userid = session_id);
	
	if resultset = 0 then
		set grp_form = -1;
	else
		set resultset = (select grpid from stdgroup where grpid in (select grpid from student where userid = session_id and grpid <> 0) and is_active <> 0);
		if resultset is null then
			set grp_form = 0;
		else
			set grp_form = resultset;
		end if;	
	end if;	
	select grp_form;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `check_sequence` (IN `group_id` INT, IN `template` VARCHAR(255))  begin
	declare val int;
	declare resultset varchar(255);
	
	if group_id = -1 then
		set resultset = "form_group";
	elseif group_id <> -1 and template = "form_group" then
		set resultset = "form_group";
	elseif group_id = 0 then
		set resultset = "form_group";
	else	
		set val = (select grpid from group_domain where grpid = group_id limit 1);
		if val is null then
			set resultset = "submit_domain";
		elseif val is not null and template = "submit_domain" then
			set resultset = "submit_domain";
		else 
			set val = (select grpid from guide_preferences where grpid = group_id limit 1);
			if val is null then
				set resultset = "guide_preference";
			elseif val is not null and template = "guide_preference" then
				set resultset = "guide_preference";
			else
				set val = (select grpid from abstract where grpid = group_id limit 1);
				if val is null then
					set resultset = "abstract_project";
				elseif val is not null and template = "abstract_project" then
					set resultset = "abstract_project";
				end if;	
			end if;
		end if;
	
	end if;
	
	select resultset;
	
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `check_submit_domain` ()  begin
	declare group_domain int;
	set group_domain = (select count(grpid) from group_domain);
	if group_domain = 0 then
		 select grpid, name, username from user, student where student.userid = user.userid and student.grpid = 0;
	else
		select stdgroup.grpid, username, name from student, user, stdgroup where student.userid = user.userid and stdgroup.grpid = student.grpid and stdgroup.grpid not in (select stdgroup.grpid from stdgroup join group_domain on stdgroup.grpid = group_domain.grpid group by grpid) union select grpid, username, name from user, student where student.userid = user.userid and student.grpid = 0;
	end if;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `check_submit_guide_preferences` ()  begin
	declare group_domain int;
	set group_domain = (select count(grpid) from guide_preferences);
	if group_domain = 0 then
		select grpid, name, username from user, student where student.userid = user.userid and student.grpid = 0;
	else
		select stdgroup.grpid, username, name from student, user, stdgroup where student.userid = user.userid and stdgroup.grpid = student.grpid and stdgroup.grpid not in (select stdgroup.grpid from stdgroup join guide_preferences on stdgroup.grpid = guide_preferences.grpid group by grpid) union select grpid, username, name from user, student where student.userid = user.userid and student.grpid = 0;
	end if;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `check_submit_synopsis` ()  begin
	declare group_domain int;
	set group_domain = (select count(grpid) from abstract);
	if group_domain = 0 then
		select grpid, name, username from user, student where student.userid = user.userid and student.grpid = 0;
	else
		select stdgroup.grpid, username, name from student, user, stdgroup where student.userid = user.userid and stdgroup.grpid = student.grpid and stdgroup.grpid not in (select stdgroup.grpid from stdgroup join abstract on stdgroup.grpid = abstract.grpid group by grpid) union select grpid, username, name from user, student where student.userid = user.userid and student.grpid = 0;
	end if;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `prepare_group` (IN `msg_id` INT)  begin
	
	declare done int default false;
	declare i int default 0;
	declare grpid int default 0;
	declare sponsor_id int;
	declare column_name varchar(20);
	declare maximum int;
	declare query varchar(255);
	declare sender varchar(255);
	declare receiver varchar(255);
	declare c1 cursor for select userid, receipient_id from message, msg_receipient where message.msgid = msg_receipient.msgid and message.msgid = msg_id and message.count_receipients = (select count(is_read) from msg_receipient where msgid = msg_id and is_read = 1);
	declare continue handler for not found set done = true;
	open c1;
	
	set maximum = (select count(grpid) from stdgroup);
	read_loop:loop
		fetch c1 into sender, receiver;
		if done then
			leave read_loop;
		end if;
		if i = 0 then
			update student set grpid = maximum + 1 where userid = sender;
			update student set grpid = maximum + 1 where userid = receiver; 
		else
			update student set grpid = maximum + 1 where userid = receiver; 
		end if;	
		set i = i + 1;
	end loop;	
	
	
	set sender = (select distinct userid from message, msg_receipient where message.msgid = msg_receipient.msgid and message.msgid = msg_id and message.count_receipients = (select count(is_read) from msg_receipient where msgid = msg_id and is_read = 1));
	if sender is not null then		
		insert into sponsorship (company_name, external_guide) values ("N/A", "N/A");
		insert into stdgroup (guideid, sponsor_id) values (NULL, maximum + 1);
		set @query = concat("alter table checklist add column grpid", maximum + 1, " int default 4");
		prepare stmt from @query;
		execute stmt;
		deallocate prepare stmt;
	end if;

	
close c1;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `abstract`
--

CREATE TABLE `abstract` (
  `id` int(11) NOT NULL,
  `grpid` int(11) NOT NULL,
  `background` text NOT NULL,
  `purpose` text NOT NULL,
  `method` text NOT NULL,
  `results` text NOT NULL,
  `conclusion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `capability`
--

CREATE TABLE `capability` (
  `cid` int(11) NOT NULL,
  `cname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `capability`
--

INSERT INTO `capability` (`cid`, `cname`) VALUES
(1000, 'co-ordinator'),
(1001, 'commitee-member'),
(1002, 'commitee-assistant'),
(1003, 'guide'),
(1004, 'reviewer');

-- --------------------------------------------------------

--
-- Table structure for table `checklist`
--

CREATE TABLE `checklist` (
  `id` int(11) NOT NULL,
  `question` text,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `checklist`
--

INSERT INTO `checklist` (`id`, `question`, `type`) VALUES
(1, 'Is the statement short and concise (10-20 words maximum)?', 1),
(2, 'Does the statement gives clear indication about what your project will accomplish?', 1),
(3, 'Can a person who is not familiar with the project understand scope of the project by reading the Project Problem Statement?', 1),
(4, 'Are all aspects of the requirements document (i.e., Functional Spec.) addressed in the design?', 1),
(5, 'Is the architecture / block diagram well defined and understood?', 1),
(6, 'The project\'s objective of study(what product, process, resource etc.) is being addressed?', 1),
(7, 'The project\'s purpose: is the purpose of project addressed properly (why it\'s being pursued: to evaluate, reduce, increase etc', 1),
(8, 'The project\'s viewpoint: Is the project’s viewpoint is understood? (Who is the project\'s end user)?', 1),
(9, 'Is the project goal statement is in alignment with the sponsoring organization’s business goals and mission?', 1),
(10, 'Is information domain analysis complete, consistent and accurate?', 1),
(11, 'Is problem statement categorized in identified area and targeted towards specific area therein?', 1),
(12, 'Are external and internal interfaces properly defined?', 1),
(13, 'Does the Use Case Model properly reflects the actors and their roles and responsibilities?', 1),
(14, 'Are all requirements traceable to system level?', 1),
(15, 'Is similar type of methodology / model is used for existing work?', 1),
(16, 'Are requirements consistent with schedule, resources and budget?', 1),
(17, 'Are requirements reflected in the system architecture?', 2),
(18, 'Does the design support both project (product) and project goals?', 2),
(19, 'Does the design address all the issues from the requirements?', 2),
(20, 'Is effective modularity achieved and modules are functionally independent?', 2),
(21, 'Are structural diagrams (Class, Object, etc.) well defined and understood?', 2),
(22, 'Are all class associations clearly defined and understood? (Is it clear which classes provide which services)?', 2),
(23, 'Are the classes in the class diagram clear? (What they represent in the architecture design document)?', 2),
(24, 'Is inheritance appropriately used?', 2),
(25, 'Are the multiplicities in the use case diagram depicted in the class diagram?', 2),
(26, 'Are behavioral diagrams (use case, sequence, activity, etc.) well defined and understood?', 2),
(27, 'Is aggregation/containment (if used) clearly defined and understood?', 2),
(28, 'Does each case have clearly defined actors and input/output?', 2),
(29, 'Is all concurrent processing (if used) clearly understood and reflected in the sequence diagrams?', 2),
(30, 'Are all objects used in sequence diagram?', 2),
(31, 'Does the sequence diagram match class diagram?', 2),
(32, 'Are the symbols used in all diagrams correspond to UML standards?', 2),
(33, 'Does the code completely and correctly implement the design?', 3),
(34, 'Does the code comply with the Coding Standards?', 3),
(35, 'Is the code well-structured, consistent in style, and consistently formatted?', 3),
(36, 'Does the implementation match the design?', 3),
(37, 'Are all functions in the design coded?', 3),
(38, 'Is the code clearly and adequately documented?', 3),
(39, 'Are all comments consistent with the code?', 3),
(40, 'Is every feature tested?', 4),
(41, 'Are all functions, user screens and navigation tested? (e.g. module, object, integration, usability, system)', 4),
(42, 'Are test cases designed? (manual and automated)', 4),
(43, 'Is testing tool used?', 4),
(44, 'Is result analysis done properly and appropriate conclusion drawn?', 4),
(45, 'Implementation status ( code completion in percentage)?', 4),
(46, 'Final thesis status( in percentage)', 4);

-- --------------------------------------------------------

--
-- Table structure for table `domain`
--

CREATE TABLE `domain` (
  `did` int(11) NOT NULL,
  `dname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `domain`
--

INSERT INTO `domain` (`did`, `dname`) VALUES
(1, 'Architectures, Compiler Optimization, and Embedded Systems'),
(2, 'Bioinformatics and Computational Biology'),
(3, 'Artificial Intelligence and Machine Learning'),
(4, 'Natural Language Processing and Information Retrieval'),
(5, 'Databases and Data Mining, Geographical Information Systems'),
(6, ' Embedded and Mobile Systems'),
(7, ' Human-Computer Interaction'),
(8, 'Graphics and Visualization'),
(9, '  High Performance Computing'),
(10, ' Networks, Distributed Systems and Security'),
(11, '  Software Engineering, Project Management');

-- --------------------------------------------------------

--
-- Table structure for table `group_domain`
--

CREATE TABLE `group_domain` (
  `grpid` int(11) NOT NULL,
  `did` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `grplog`
--

CREATE TABLE `grplog` (
  `id` int(11) NOT NULL,
  `log` text,
  `grpid` int(11) DEFAULT NULL,
  `logdate` date DEFAULT NULL,
  `log_staff` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `grp_reviewer`
--

CREATE TABLE `grp_reviewer` (
  `tid` int(11) NOT NULL,
  `grpid` int(11) NOT NULL,
  `review1` text,
  `review2` text,
  `review3` text,
  `review4` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `guide_preferences`
--

CREATE TABLE `guide_preferences` (
  `grpid` int(11) NOT NULL,
  `guideid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `msgid` int(11) NOT NULL,
  `msg_body` text,
  `userid` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `count_receipients` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `msg_receipient`
--

CREATE TABLE `msg_receipient` (
  `msgrecid` int(11) NOT NULL,
  `receipient_id` varchar(255) DEFAULT NULL,
  `is_read` tinyint(4) DEFAULT NULL,
  `msgid` int(11) DEFAULT NULL,
  `is_declined` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `review_1`
--

CREATE TABLE `review_1` (
  `id` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  `scope` int(11) NOT NULL,
  `survey` int(11) NOT NULL,
  `planning` int(11) NOT NULL,
  `skills` int(11) NOT NULL,
  `qa` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `review_2`
--

CREATE TABLE `review_2` (
  `id` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `survey` varchar(10) DEFAULT NULL,
  `design` int(11) NOT NULL,
  `features` int(11) NOT NULL,
  `planning` int(11) NOT NULL,
  `basic` int(11) NOT NULL,
  `skills` int(11) NOT NULL,
  `qa` int(11) NOT NULL,
  `summarize` varchar(10) DEFAULT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `review_3`
--

CREATE TABLE `review_3` (
  `id` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `design` varchar(10) DEFAULT NULL,
  `implementation` int(11) NOT NULL,
  `results` int(11) NOT NULL,
  `skills` int(11) NOT NULL,
  `qa` int(11) NOT NULL,
  `summarize` varchar(10) DEFAULT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `review_4`
--

CREATE TABLE `review_4` (
  `id` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `implementation` int(11) NOT NULL,
  `testing` int(11) NOT NULL,
  `report` int(11) NOT NULL,
  `publications` int(11) NOT NULL,
  `skills` int(11) NOT NULL,
  `qa` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `sch_id` int(11) NOT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `notification` text,
  `template` varchar(255) DEFAULT NULL,
  `is_active` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`sch_id`, `start_date`, `expiry_date`, `notification`, `template`, `is_active`) VALUES
(1, '2018-10-21', '2018-10-24', 'Project Group formation. Group should be of maximum 4 peoples and minimum of 3 peoples.', 'form_group.jsp', 0),
(2, '2018-10-31', '2018-11-05', 'Submit project domain related information alongwith sponsorship details.', 'submit_domain.jsp', 0),
(3, '2018-11-14', '2018-11-18', 'Submit guide preferences', 'guide_preference.jsp', 0),
(4, '2018-11-22', '2018-11-27', 'Submit project synopsis', 'abstract_project.jsp', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sponsorship`
--

CREATE TABLE `sponsorship` (
  `sponsor_id` int(11) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `external_guide` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `stdgroup`
--

CREATE TABLE `stdgroup` (
  `grpid` int(11) NOT NULL,
  `project_title` varchar(255) DEFAULT NULL,
  `project_domain` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) DEFAULT '0',
  `guidename` varchar(255) DEFAULT NULL,
  `is_active` int(11) DEFAULT '0',
  `guideid` int(11) DEFAULT '0',
  `pbs_id` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `sid` int(11) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `grpid` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `tid` int(11) NOT NULL,
  `userid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`tid`, `userid`) VALUES
(511, 'em2019pict2');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_capability`
--

CREATE TABLE `teacher_capability` (
  `tid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher_capability`
--

INSERT INTO `teacher_capability` (`tid`, `cid`) VALUES
(511, 1002);

-- --------------------------------------------------------

--
-- Table structure for table `teacher_domain`
--

CREATE TABLE `teacher_domain` (
  `tid` int(11) NOT NULL,
  `did` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobileno` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `name`, `email`, `mobileno`) VALUES
('em2019pict2', 'pict', 'admin', NULL, NULL, NULL);

--
-- Triggers `user`
--
DELIMITER $$
CREATE TRIGGER `insert_into_child_tables` AFTER INSERT ON `user` FOR EACH ROW begin
	declare user_type varchar(5);
	declare capability varchar(5);
	declare teacher_id int;
	declare sid int;
	set user_type = (select substr(new.userid, 1, 2));
	if user_type = "st" then
		
		insert into student(userid) values (new.userid);
		set sid = (select last_insert_id() from student limit 1);
		insert into review_1 (sid, topic, scope, survey, planning, skills, qa, total) values (sid, 0, 0, 0, 0, 0, 0, 0);
		insert into review_2 (sid, survey, design, features, planning, basic, skills, qa, summarize, total) values (sid, 'n', 0, 0, 0, 0, 0, 0, 'n', 0);
		insert into review_3 (sid, design, implementation, results, skills, qa, summarize, total) values (sid, 'n', 0, 0, 0, 0, 'n', 0);
		insert into review_4 (sid, implementation, testing, report, publications, skills, qa, total) values (sid, 0, 0, 0, 0, 0, 0, 0);
	elseif user_type = "em" then
	
			
		insert into teacher(userid) values (new.userid);
		
		
		
		set teacher_id = (select max(tid) from teacher);
		
		
		set capability = (select substr(new.userid, length(concat("em", year(now()), new.username)) + 1, length(new.userid)));
		
		
		if locate("0", capability) then
			insert into teacher_capability(tid, cid) values (teacher_id, 1000);
		end if;
		if locate("1", capability) then
			insert into teacher_capability(tid, cid) values (teacher_id, 1001);
		end if;	
		if locate("2", capability) then
			insert into teacher_capability(tid, cid) values (teacher_id, 1002);
		end if;	
		if locate("3", capability) then
			insert into teacher_capability(tid, cid) values (teacher_id, 1003);
		end if;	
		if locate("4", capability) then
			insert into teacher_capability(tid, cid) values (teacher_id, 1004);	
		end if;
	end if;
end
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abstract`
--
ALTER TABLE `abstract`
  ADD PRIMARY KEY (`id`),
  ADD KEY `grpid` (`grpid`);

--
-- Indexes for table `capability`
--
ALTER TABLE `capability`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `checklist`
--
ALTER TABLE `checklist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `domain`
--
ALTER TABLE `domain`
  ADD PRIMARY KEY (`did`);

--
-- Indexes for table `group_domain`
--
ALTER TABLE `group_domain`
  ADD PRIMARY KEY (`grpid`,`did`),
  ADD KEY `did` (`did`);

--
-- Indexes for table `grplog`
--
ALTER TABLE `grplog`
  ADD PRIMARY KEY (`id`),
  ADD KEY `grpid` (`grpid`);

--
-- Indexes for table `grp_reviewer`
--
ALTER TABLE `grp_reviewer`
  ADD PRIMARY KEY (`tid`,`grpid`),
  ADD KEY `grpid` (`grpid`);

--
-- Indexes for table `guide_preferences`
--
ALTER TABLE `guide_preferences`
  ADD PRIMARY KEY (`grpid`,`guideid`),
  ADD KEY `guideid` (`guideid`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`msgid`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `msg_receipient`
--
ALTER TABLE `msg_receipient`
  ADD PRIMARY KEY (`msgrecid`),
  ADD KEY `receipient_id` (`receipient_id`),
  ADD KEY `msgid` (`msgid`);

--
-- Indexes for table `review_1`
--
ALTER TABLE `review_1`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sid` (`sid`);

--
-- Indexes for table `review_2`
--
ALTER TABLE `review_2`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sid` (`sid`);

--
-- Indexes for table `review_3`
--
ALTER TABLE `review_3`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sid` (`sid`);

--
-- Indexes for table `review_4`
--
ALTER TABLE `review_4`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sid` (`sid`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`sch_id`);

--
-- Indexes for table `sponsorship`
--
ALTER TABLE `sponsorship`
  ADD PRIMARY KEY (`sponsor_id`);

--
-- Indexes for table `stdgroup`
--
ALTER TABLE `stdgroup`
  ADD PRIMARY KEY (`grpid`),
  ADD KEY `sponsor_id` (`sponsor_id`),
  ADD KEY `guideid` (`guideid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`sid`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`tid`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `teacher_capability`
--
ALTER TABLE `teacher_capability`
  ADD KEY `tid` (`tid`),
  ADD KEY `cid` (`cid`);

--
-- Indexes for table `teacher_domain`
--
ALTER TABLE `teacher_domain`
  ADD PRIMARY KEY (`tid`,`did`),
  ADD KEY `did` (`did`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `abstract`
--
ALTER TABLE `abstract`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `capability`
--
ALTER TABLE `capability`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;

--
-- AUTO_INCREMENT for table `checklist`
--
ALTER TABLE `checklist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `domain`
--
ALTER TABLE `domain`
  MODIFY `did` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `grplog`
--
ALTER TABLE `grplog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `msgid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `msg_receipient`
--
ALTER TABLE `msg_receipient`
  MODIFY `msgrecid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review_1`
--
ALTER TABLE `review_1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review_2`
--
ALTER TABLE `review_2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review_3`
--
ALTER TABLE `review_3`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review_4`
--
ALTER TABLE `review_4`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `sch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sponsorship`
--
ALTER TABLE `sponsorship`
  MODIFY `sponsor_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stdgroup`
--
ALTER TABLE `stdgroup`
  MODIFY `grpid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=512;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `abstract`
--
ALTER TABLE `abstract`
  ADD CONSTRAINT `abstract_ibfk_1` FOREIGN KEY (`grpid`) REFERENCES `stdgroup` (`grpid`);

--
-- Constraints for table `group_domain`
--
ALTER TABLE `group_domain`
  ADD CONSTRAINT `group_domain_ibfk_1` FOREIGN KEY (`grpid`) REFERENCES `stdgroup` (`grpid`),
  ADD CONSTRAINT `group_domain_ibfk_2` FOREIGN KEY (`did`) REFERENCES `domain` (`did`);

--
-- Constraints for table `grplog`
--
ALTER TABLE `grplog`
  ADD CONSTRAINT `grplog_ibfk_1` FOREIGN KEY (`grpid`) REFERENCES `stdgroup` (`grpid`);

--
-- Constraints for table `grp_reviewer`
--
ALTER TABLE `grp_reviewer`
  ADD CONSTRAINT `grp_reviewer_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`),
  ADD CONSTRAINT `grp_reviewer_ibfk_2` FOREIGN KEY (`grpid`) REFERENCES `stdgroup` (`grpid`);

--
-- Constraints for table `guide_preferences`
--
ALTER TABLE `guide_preferences`
  ADD CONSTRAINT `guide_preferences_ibfk_1` FOREIGN KEY (`grpid`) REFERENCES `stdgroup` (`grpid`),
  ADD CONSTRAINT `guide_preferences_ibfk_2` FOREIGN KEY (`guideid`) REFERENCES `teacher` (`tid`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `msg_receipient`
--
ALTER TABLE `msg_receipient`
  ADD CONSTRAINT `msg_receipient_ibfk_1` FOREIGN KEY (`receipient_id`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `msg_receipient_ibfk_2` FOREIGN KEY (`msgid`) REFERENCES `message` (`msgid`);

--
-- Constraints for table `review_1`
--
ALTER TABLE `review_1`
  ADD CONSTRAINT `review_1_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`);

--
-- Constraints for table `review_2`
--
ALTER TABLE `review_2`
  ADD CONSTRAINT `review_2_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`);

--
-- Constraints for table `review_3`
--
ALTER TABLE `review_3`
  ADD CONSTRAINT `review_3_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`);

--
-- Constraints for table `review_4`
--
ALTER TABLE `review_4`
  ADD CONSTRAINT `review_4_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`);

--
-- Constraints for table `stdgroup`
--
ALTER TABLE `stdgroup`
  ADD CONSTRAINT `stdgroup_ibfk_1` FOREIGN KEY (`guideid`) REFERENCES `teacher` (`tid`);

--
-- Constraints for table `teacher_capability`
--
ALTER TABLE `teacher_capability`
  ADD CONSTRAINT `teacher_capability_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`),
  ADD CONSTRAINT `teacher_capability_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `capability` (`cid`);

--
-- Constraints for table `teacher_domain`
--
ALTER TABLE `teacher_domain`
  ADD CONSTRAINT `teacher_domain_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`),
  ADD CONSTRAINT `teacher_domain_ibfk_2` FOREIGN KEY (`did`) REFERENCES `domain` (`did`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `delete_on_expiry` ON SCHEDULE EVERY 1 HOUR STARTS '2018-10-09 06:24:56' ON COMPLETION NOT PRESERVE ENABLE DO delete from broadcast where expiry_date > start_date$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
