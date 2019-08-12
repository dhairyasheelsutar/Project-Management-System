package models;

import java.util.Map;
import java.util.List;

//General functions of backend written in functions.java

public class Functions {

	public String trim_first_last(Map<String, String> key_value_pair) {
		String str = key_value_pair.toString().substring(1, key_value_pair.toString().length()-1);
		return str;
	}
	
	public String primary_key_array(Map<String, String> key_value_pair) {
		String str = key_value_pair.toString().substring(1, key_value_pair.toString().length()-1);
		str = str.replace(", ", " AND ");
		return str;
	}
	
	public String set_output_message(String message, boolean type) {
		String msg = "";
		if(type == true) {
			msg = "<div class='alert alert-success alert-dismissible fade show'>\r\n" + 
					"    <button type='button' class='close' data-dismiss='alert'>&times;</button>\r\n" + 
					"    "+ message +"\r\n" + 
					"  </div>";
		}else {
			msg = "<div class='alert alert-danger alert-dismissible fade show'>\r\n" + 
					"    <button type='button' class='close' data-dismiss='alert'>&times;</button>\r\n" + 
					"    "+ message +"\r\n" + 
					"  </div>";
		}
		return msg;
	}
	
	public String print_radio_for_login( int id, String arg ) {
		String html = "<div class='text-center'>\r\n" + 
				"  <input  name='radiobtn' value='"+ id +"' id='customRadio1' type='radio'>\r\n" + 
				"  <label for='radiobtn'>"+ arg +"</label>\r\n" + 
				"</div>";
		return html;
		
	}
	
	public String generate_review_html(int participants, String[] attr, String[] val, List<List<Object>>arraylist) {
		String output = "";
		
		int count = 2;
		for(int i=0;i<attr.length;i++) {
			int j = 0;
			
			//for 4 members generate 4 columns
			
			if (j <= 3 && participants == 4) {
				output += "<tr class='text-center'>";
				output += "<td>"+ attr[i] +"</td>";
				output += "<td><input type='text' class='form-control' name='"+ val[i] +"' value='"+ arraylist.get(j++).get(count) +"' onkeypress='return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57' required></td>";
				output += "<td><input type='text' class='form-control' name='"+ val[i] +"' value='"+ arraylist.get(j++).get(count) +"' onkeypress='return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57' required></td>";
				output += "<td><input type='text' class='form-control' name='"+ val[i] +"' value='"+ arraylist.get(j++).get(count) +"' onkeypress='return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57' required></td>";
				output += "<td><input type='text' class='form-control' name='"+ val[i] +"' value='"+ arraylist.get(j++).get(count) +"' onkeypress='return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57' required></td>";
				output += "</tr>";
			}else if(j <= 2 && participants == 3) {
				
				//for 3 members generate 3 columns
				
				output += "<tr class='text-center'>";
				output += "<td>"+ attr[i] +"</td>";
				output += "<td><input type='text' class='form-control' name='"+ val[i] +"' value='"+ arraylist.get(j++).get(count) +"' onkeypress='return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57' required></td>";
				output += "<td><input type='text' class='form-control' name='"+ val[i] +"' value='"+ arraylist.get(j++).get(count) +"' onkeypress='return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57' required></td>";
				output += "<td><input type='text' class='form-control' name='"+ val[i] +"' value='"+ arraylist.get(j++).get(count) +"' onkeypress='return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57' required></td>";
				output += "</tr>";
			}
			
			count++;
		}
		return output;
	}
	
	public String generate_arraylist_html(int start, int end, int type, List<List<Object>> arraylist, String[] values, boolean display) {
		
		String output = "<div style='max-height: 700px; overflow-y: auto;'>";
		
		for(int i=start;i<end;i++) {
			
			int value = (int)arraylist.get(i).get(2);
			output += "<div class='px-3 py-2' style='border-top: 1px solid #d3d3d3'>";
			output += "<span style='font-size: 14px; text-align: justify'>" + i + ") " + arraylist.get(i).get(0) + "</span><br>";
			for(int j=0;j<4;j++) {
				int val = j + 1;
				if(value == val) {
					output += "<input type='radio' name='check1list"+ i +"' value='"+ val +"' checked />";
				}else {
					output += "<input type='radio' name='check1list"+ i +"' value='"+ val +"' />";
				}
				output += "<label style='font-size: 14px;' class='ml-1 mr-3'>"+ values[j] +"</label>";
			}
			output += "</div>";
			
		}
		
		if(display) {
			output += "<div class='mt-3 text-center'><input type='submit' name='submit' value='submit' class='btn btn-warning' /></div>";
		}
		
		output += "</div>";
		
		
		return output;
	}
	
	public String generate_sidebar_menu(String active, String text_color, String menu, String href) {
		String output = "";
		output += "<li class='nav-item "+ active +"'><a href='"+ href +"' class='nav-link "+ text_color +"'>"+ menu +"</a></li>";
		return output;
	}
	
}
