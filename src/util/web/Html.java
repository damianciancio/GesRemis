package util.web;

public class Html {
	public static String getLinksStylesAndScripts(){
		return "<script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-3.1.1.js\"></script>\n"
				+ "<script type=\"text/javascript\" src=\"js/jquery.validate.js\"></script>\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap/css/bootstrap.css\">\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap/css/bootstrap-theme.css\">\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap/js/bootstrap.js\">\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap/js/npm.js\">\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"datepicker/css/datepicker.css\">\n"
				+ "<script type=\"text/javascript\" src=\"datepicker/js/bootstrap-datepicker.js\"></script>\n"
				+ "<script type=\"text/javascript\" src=\"js/scripts.js\"></script>\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">";
	}
}
