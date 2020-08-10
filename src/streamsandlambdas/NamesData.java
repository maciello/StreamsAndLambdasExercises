package streamsandlambdas;

import java.util.Arrays;
import java.util.Random;

public class NamesData {
    private final String[] maleFirstNames;
    private final String[] femaleFirstNames;
    private final String[] lastNames;
    private final Random r = new Random(1337);
    public static NamesData names = NamesData.getInstance();

    private static NamesData getInstance() {
        if (names == null) {
            names = new NamesData();
        }
        return names;
    }

    public NamesData() {
        maleFirstNames = generateMaleFirstNames();
        femaleFirstNames = generateFemaleFirstNames();
        lastNames = generateLastNames();
    }

    private String[] generateMaleFirstNames() {
        return new String[] { "JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM", "DAVID", "RICHARD", "CHARLES",
                              "JOSEPH", "THOMAS", "CHRISTOPHER", "DANIEL", "PAUL", "MARK", "DONALD",
                              "GEORGE", "KENNETH", "STEVEN", "EDWARD", "BRIAN", "RONALD", "ANTHONY", "KEVIN",
                              "JASON", "MATTHEW", "GARY", "TIMOTHY", "JOSE", "LARRY", "JEFFREY", "FRANK",
                              "SCOTT", "ERIC", "STEPHEN", "ANDREW", "RAYMOND", "GREGORY", "JOSHUA", "JERRY",
                              "DENNIS", "WALTER", "PATRICK", "PETER", "HAROLD", "DOUGLAS", "HENRY", "CARL",
                              "ARTHUR", "RYAN", "ROGER", "JOE", "JUAN", "JACK", "ALBERT", "JONATHAN",
                              "JUSTIN", "TERRY", "GERALD", "KEITH", "SAMUEL", "WILLIE", "RALPH", "LAWRENCE",
                              "NICHOLAS", "ROY", "BENJAMIN", "BRUCE", "BRANDON", "ADAM", "HARRY", "FRED",
                              "WAYNE", "BILLY", "STEVE", "LOUIS", "JEREMY", "AARON", "RANDY", "HOWARD",
                              "EUGENE", "CARLOS", "RUSSELL", "BOBBY", "VICTOR", "MARTIN", "ERNEST",
                              "PHILLIP", "TODD", "JESSE", "CRAIG", "ALAN", "SHAWN", "CLARENCE", "SEAN",
                              "PHILIP", "CHRIS", "JOHNNY", "EARL", "JIMMY", "ANTONIO", "DANNY", "BRYAN",
                              "TONY", "LUIS", "MIKE", "STANLEY", "LEONARD", "NATHAN", "DALE", "MANUEL",
                              "RODNEY", "CURTIS", "NORMAN", "ALLEN", "MARVIN", "VINCENT", "GLENN", "JEFFERY",
                              "TRAVIS", "JEFF", "CHAD", "JACOB", "LEE", "MELVIN", "ALFRED", "KYLE",
                              "FRANCIS", "BRADLEY", "JESUS", "HERBERT", "FREDERICK", "RAY", "JOEL", "EDWIN",
                              "DON", "EDDIE", "RICKY", "TROY", "RANDALL", "BARRY", "ALEXANDER", "BERNARD",
                              "MARIO", "LEROY", "FRANCISCO", "MARCUS", "MICHEAL", "THEODORE", "CLIFFORD",
                              "MIGUEL", "OSCAR", "JAY", "JIM", "TOM", "CALVIN", "ALEX", "JON", "RONNIE",
                              "BILL", "LLOYD", "TOMMY", "LEON", "DEREK", "WARREN", "DARRELL", "JEROME",
                              "FLOYD", "LEO", "ALVIN", "TIM", "WESLEY", "GORDON", "DEAN", "GREG", "JORGE",
                              "DUSTIN", "PEDRO", "DERRICK", "DAN", "LEWIS", "ZACHARY", "COREY", "HERMAN",
                              "MAURICE", "VERNON", "ROBERTO", "CLYDE", "GLEN", "HECTOR", "SHANE", "RICARDO",
                              "SAM", "RICK", "LESTER", "BRENT", "RAMON", "CHARLIE", "TYLER", "GILBERT",
                              "GENE", "MARC", "REGINALD", "RUBEN", "BRETT", "ANGEL", "NATHANIEL", "RAFAEL",
                              "LESLIE", "EDGAR", "MILTON", "RAUL", "BEN", "CHESTER", "CECIL", "DUANE",
                              "FRANKLIN", "ANDRE", "ELMER", "BRAD", "GABRIEL", "RON", "MITCHELL", "ROLAND",
                              "ARNOLD", "HARVEY", "JARED", "ADRIAN", "KARL", "CORY", "CLAUDE", "ERIK",
                              "DARRYL", "JAMIE", "NEIL", "JESSIE", "CHRISTIAN", "JAVIER", "FERNANDO",
                              "CLINTON", "TED", "MATHEW", "TYRONE", "DARREN", "LONNIE", "LANCE", "CODY",
                              "JULIO", "KELLY", "KURT", "ALLAN", "NELSON", "GUY", "CLAYTON", "HUGH", "MAX",
                              "DWAYNE", "DWIGHT", "ARMANDO", "FELIX", "JIMMIE", "EVERETT", "JORDAN", "IAN",
                              "WALLACE", "KEN", "BOB", "JAIME", "CASEY", "ALFREDO", "ALBERTO", "DAVE",
                              "IVAN", "JOHNNIE", "SIDNEY", "BYRON", "JULIAN", "ISAAC", "MORRIS", "CLIFTON",
                              "WILLARD", "DARYL", "ROSS", "VIRGIL", "ANDY", "MARSHALL", "SALVADOR", "PERRY",
                              "KIRK", "SERGIO", "MARION", "TRACY", "SETH", "KENT", "TERRANCE", "RENE",
                              "EDUARDO", "TERRENCE", "ENRIQUE", "FREDDIE", "WADE" };
    }

    private static String[] generateFemaleFirstNames() {
        return new String[] { "MARY", "PATRICIA", "LINDA", "BARBARA", "ELIZABETH", "JENNIFER", "MARIA", "SUSAN",
                      "MARGARET", "DOROTHY", "LISA", "NANCY", "KAREN", "BETTY", "HELEN", "SANDRA", "DONNA",
                      "CAROL", "RUTH", "SHARON", "MICHELLE", "LAURA", "SARAH", "KIMBERLY", "DEBORAH",
                      "JESSICA", "SHIRLEY", "CYNTHIA", "ANGELA", "MELISSA", "BRENDA", "AMY", "ANNA",
                      "REBECCA", "VIRGINIA", "KATHLEEN", "PAMELA", "MARTHA", "DEBRA", "AMANDA", "STEPHANIE",
                      "CAROLYN", "CHRISTINE", "MARIE", "JANET", "CATHERINE", "FRANCES", "ANN", "JOYCE",
                      "DIANE", "ALICE", "JULIE", "HEATHER", "TERESA", "DORIS", "GLORIA", "EVELYN", "JEAN",
                      "CHERYL", "MILDRED", "KATHERINE", "JOAN", "ASHLEY", "JUDITH", "ROSE", "JANICE", "KELLY",
                      "NICOLE", "JUDY", "CHRISTINA", "KATHY", "THERESA", "BEVERLY", "DENISE", "TAMMY",
                      "IRENE", "JANE", "LORI", "RACHEL", "MARILYN", "ANDREA", "KATHRYN", "LOUISE", "SARA",
                      "ANNE", "JACQUELINE", "WANDA", "BONNIE", "JULIA", "RUBY", "LOIS", "TINA", "PHYLLIS",
                      "NORMA", "PAULA", "DIANA", "ANNIE", "LILLIAN", "EMILY", "ROBIN", "PEGGY", "CRYSTAL",
                      "GLADYS", "RITA", "DAWN", "CONNIE", "FLORENCE", "TRACY", "EDNA", "TIFFANY", "CARMEN",
                      "ROSA", "CINDY", "GRACE", "WENDY", "VICTORIA", "EDITH", "KIM", "SHERRY", "SYLVIA",
                      "JOSEPHINE", "THELMA", "SHANNON", "SHEILA", "ETHEL", "ELLEN", "ELAINE", "MARJORIE",
                      "CARRIE", "CHARLOTTE", "MONICA", "ESTHER", "PAULINE", "EMMA", "JUANITA", "ANITA",
                      "RHONDA", "HAZEL", "AMBER", "EVA", "DEBBIE", "APRIL", "LESLIE", "CLARA", "LUCILLE",
                      "JAMIE", "JOANNE", "ELEANOR", "VALERIE", "DANIELLE", "MEGAN", "ALICIA", "SUZANNE",
                      "MICHELE", "GAIL", "BERTHA", "DARLENE", "VERONICA", "JILL", "ERIN", "GERALDINE",
                      "LAUREN", "CATHY", "JOANN", "LORRAINE", "LYNN", "SALLY", "REGINA", "ERICA", "BEATRICE",
                      "DOLORES", "BERNICE", "AUDREY", "YVONNE", "ANNETTE", "JUNE", "SAMANTHA", "MARION",
                      "DANA", "STACY", "ANA", "RENEE", "IDA", "VIVIAN", "ROBERTA", "HOLLY", "BRITTANY",
                      "MELANIE", "LORETTA", "YOLANDA", "JEANETTE", "LAURIE", "KATIE", "KRISTEN", "VANESSA",
                      "ALMA", "SUE", "ELSIE", "BETH", "JEANNE", "VICKI", "CARLA", "TARA", "ROSEMARY",
                      "EILEEN", "TERRI", "GERTRUDE", "LUCY", "TONYA", "ELLA", "STACEY", "WILMA", "GINA",
                      "KRISTIN", "JESSIE", "NATALIE", "AGNES", "VERA", "WILLIE", "CHARLENE", "BESSIE",
                      "DELORES", "MELINDA", "PEARL", "ARLENE", "MAUREEN", "COLLEEN", "ALLISON", "TAMARA",
                      "JOY", "GEORGIA", "CONSTANCE", "LILLIE", "CLAUDIA", "JACKIE", "MARCIA", "TANYA",
                      "NELLIE", "MINNIE", "MARLENE", "HEIDI", "GLENDA", "LYDIA", "VIOLA", "COURTNEY",
                      "MARIAN", "STELLA", "CAROLINE", "DORA", "JO", "VICKIE", "MATTIE", "TERRY", "MAXINE",
                      "IRMA", "MABEL", "MARSHA", "MYRTLE", "LENA", "CHRISTY", "DEANNA", "PATSY", "HILDA",
                      "GWENDOLYN", "JENNIE", "NORA", "MARGIE", "NINA", "CASSANDRA", "LEAH", "PENNY", "KAY",
                      "PRISCILLA", "NAOMI", "CAROLE", "BRANDY", "OLGA", "BILLIE", "DIANNE", "TRACEY", "LEONA",
                      "JENNY", "FELICIA", "SONIA", "MIRIAM", "VELMA", "BECKY", "BOBBIE", "VIOLET", "KRISTINA",
                      "TONI", "MISTY", "MAE", "SHELLY", "DAISY", "RAMONA", "SHERRI", "ERIKA", "KATRINA",
                      "CLAIRE" };
    }

    private static String[] generateLastNames() {
        return new String[] { "SMITH", "JOHNSON", "WILLIAMS", "BROWN", "JONES", "GARCIA", "MILLER", "DAVIS",
                              "RODRIGUEZ", "MARTINEZ", "HERNANDEZ", "LOPEZ", "GONZALEZ", "WILSON",
                              "ANDERSON", "THOMAS", "TAYLOR", "MOORE", "JACKSON", "MARTIN", "LEE", "PEREZ",
                              "THOMPSON", "WHITE", "HARRIS", "SANCHEZ", "CLARK", "RAMIREZ", "LEWIS",
                              "ROBINSON", "WALKER", "YOUNG", "ALLEN", "KING", "WRIGHT", "SCOTT", "TORRES",
                              "NGUYEN", "HILL", "FLORES", "GREEN", "ADAMS", "NELSON", "BAKER", "HALL",
                              "RIVERA", "CAMPBELL", "MITCHELL", "CARTER", "ROBERTS", "GOMEZ", "PHILLIPS",
                              "EVANS", "TURNER", "DIAZ", "PARKER", "CRUZ", "EDWARDS", "COLLINS", "REYES",
                              "STEWART", "MORRIS", "MORALES", "MURPHY", "COOK", "ROGERS", "GUTIERREZ",
                              "ORTIZ", "MORGAN", "COOPER", "PETERSON", "BAILEY", "REED", "KELLY", "HOWARD",
                              "RAMOS", "KIM", "COX", "WARD", "RICHARDSON", "WATSON", "BROOKS", "CHAVEZ",
                              "WOOD", "JAMES", "BENNETT", "GRAY", "MENDOZA", "RUIZ", "HUGHES", "PRICE",
                              "ALVAREZ", "CASTILLO", "SANDERS", "PATEL", "MYERS", "LONG", "ROSS", "FOSTER",
                              "JIMENEZ", "POWELL", "JENKINS", "PERRY", "RUSSELL", "SULLIVAN", "BELL",
                              "COLEMAN", "BUTLER", "HENDERSON", "BARNES", "GONZALES", "FISHER", "VASQUEZ",
                              "SIMMONS", "ROMERO", "JORDAN", "PATTERSON", "ALEXANDER", "HAMILTON", "GRAHAM",
                              "REYNOLDS", "GRIFFIN", "WALLACE", "MORENO", "WEST", "COLE", "HAYES", "BRYANT",
                              "HERRERA", "GIBSON", "ELLIS", "TRAN", "MEDINA", "AGUILAR", "STEVENS", "MURRAY",
                              "FORD", "CASTRO", "MARSHALL", "OWENS", "HARRISON", "FERNANDEZ", "MCDONALD",
                              "WOODS", "WASHINGTON", "KENNEDY", "WELLS", "VARGAS", "HENRY", "CHEN",
                              "FREEMAN", "WEBB", "TUCKER", "GUZMAN", "BURNS", "CRAWFORD", "OLSON", "SIMPSON",
                              "PORTER", "HUNTER", "GORDON", "MENDEZ", "SILVA", "SHAW", "SNYDER", "MASON",
                              "DIXON", "MUNOZ", "HUNT", "HICKS", "HOLMES", "PALMER", "WAGNER", "BLACK",
                              "ROBERTSON", "BOYD", "ROSE", "STONE", "SALAZAR", "FOX", "WARREN", "MILLS",
                              "MEYER", "RICE", "SCHMIDT", "GARZA", "DANIELS", "FERGUSON", "NICHOLS",
                              "STEPHENS", "SOTO", "WEAVER", "RYAN", "GARDNER", "PAYNE", "GRANT", "DUNN",
                              "KELLEY", "SPENCER", "HAWKINS", "ARNOLD", "PIERCE", "VAZQUEZ", "HANSEN",
                              "PETERS", "SANTOS", "HART", "BRADLEY", "KNIGHT", "ELLIOTT", "CUNNINGHAM",
                              "DUNCAN", "ARMSTRONG", "HUDSON", "CARROLL", "LANE", "RILEY", "ANDREWS",
                              "ALVARADO", "RAY", "DELGADO", "BERRY", "PERKINS", "HOFFMAN", "JOHNSTON",
                              "MATTHEWS", "PENA", "RICHARDS", "CONTRERAS", "WILLIS", "CARPENTER", "LAWRENCE",
                              "SANDOVAL", "GUERRERO", "GEORGE", "CHAPMAN", "RIOS", "ESTRADA", "ORTEGA",
                              "WATKINS", "GREENE", "NUNEZ", "WHEELER", "VALDEZ", "HARPER", "BURKE", "LARSON",
                              "SANTIAGO", "MALDONADO", "MORRISON", "FRANKLIN", "CARLSON", "AUSTIN",
                              "DOMINGUEZ", "CARR", "LAWSON", "JACOBS", "OBRIEN", "LYNCH", "SINGH", "VEGA",
                              "BISHOP", "MONTGOMERY", "OLIVER", "JENSEN", "HARVEY", "WILLIAMSON", "GILBERT",
                              "DEAN", "SIMS", "ESPINOZA", "HOWELL", "LI", "WONG", "REID", "HANSON", "LE",
                              "MCCOY", "GARRETT", "BURTON", "FULLER", "WANG", "WEBER", "WELCH", "ROJAS",
                              "LUCAS", "MARQUEZ", "FIELDS", "PARK", "YANG", "LITTLE", "BANKS", "PADILLA",
                              "DAY", "WALSH", "BOWMAN", "SCHULTZ", "LUNA", "FOWLER", "MEJIA" };
    }

    public String getRandomFirstName(Gender gender) {
        return switch (gender) {
            case MALE -> maleFirstNames[r.nextInt(maleFirstNames.length)];
            case FEMALE -> femaleFirstNames[r.nextInt(maleFirstNames.length)];
        };
    }

    public String getRandomLastName() {
                return lastNames[r.nextInt(lastNames.length)];
    }

    public String[] getMaleFirstNames() {
        return Arrays.copyOf(maleFirstNames,maleFirstNames.length);
    }

    public String[] getFemaleFirstNames() {
        return Arrays.copyOf(femaleFirstNames,femaleFirstNames.length);
    }

    public String[] getLastNames() {
        return Arrays.copyOf(lastNames,lastNames.length);
    }
}
