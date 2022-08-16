package by.gsu.epamlab;

public class Constants {
    public final static String TABLE_RESULTS_CREATION = "CREATE TABLE `results`.`results` (\n" +
            "  `idlogin` INT NULL,\n" +
            "  `idtest` INT NULL,\n" +
            "  `date` DATE NULL,\n" +
            "  `mark` INT NULL,\n" +
            "  INDEX `log_fk_idx` (`idlogin` ASC) VISIBLE,\n" +
            "  INDEX `test_fk_idx` (`idtest` ASC) VISIBLE,\n" +
            "  CONSTRAINT `log_fk`\n" +
            "    FOREIGN KEY (`idlogin`)\n" +
            "    REFERENCES `results`.`logins` (`idlogin`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `test_fk`\n" +
            "    FOREIGN KEY (`idtest`)\n" +
            "    REFERENCES `results`.`tests` (`idtest`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);";
    public final static String TABLE_TESTS_CREATION = "CREATE TABLE `results`.`tests` (\n" +
            "  `idtest` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`idtest`, `name`));";
    public final static String TABLE_LOGINS_CREATION = "CREATE TABLE `results`.`logins` (\n" +
            "  `idlogin` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`idlogin`, `name`));";
    public final static String TABLE_RESULTS_DROP = "DROP TABLE `results`.`results`";
    public final static String TABLE_LOGINS_DROP = "DROP TABLE `results`.`logins`";
    public final static String TABLE_TESTS_DROP = "DROP TABLE `results`.`tests`";
    public final static String SELECT_AVERAGE_MARK = "SELECT results.logins.name as stud, \n" +
            "round(avg(results.results.mark),2) as avg_mark\n" +
            "from results.logins join results.results \n" +
            "on results.results.idlogin = results.logins.idlogin \n" +
            "group by results.logins.name\n" +
            "order by avg_mark DESC";
    public final static String CURRENT_MONTH_RESULTS = "SELECT results.logins.name as stud, results.tests.name as test,\n" +
            "results.results.date as date, results.results.mark as mark\n" +
            "from results.results join results.logins \n" +
            "on results.results.idlogin = results.logins.idlogin \n" +
            "join results.tests on results.results.idtest = results.tests.idtest\n" +
            "where month(date) = month(current_date())\n" +
            "order by day(date) ASC";
    public final static String CURRENT_MONTH_BEGINS = "2020-05-01";
    public final static String CURRENT_MONTH_ENDS = "2020-05-31";
}
