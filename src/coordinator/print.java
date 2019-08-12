package coordinator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/print")
public class print extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public print() {
        super();
    }

    private void downloadFile(final String fileName, final HttpServletResponse response){
        try {
            final File f = new File(fileName);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(f);
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
            final int size = 1024;
            try {
                response.setContentLength(fin.available());
                final byte[] buffer = new byte[size];
                ServletOutputStream os = null;

                os = response.getOutputStream();
                int length = 0;
                while ((length = fin.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                fin.close();
                os.flush();
                os.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }catch (final Exception ex){
        	
        }
            
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String filename = request.getParameter("filename");
		String form = request.getParameter("content");
		downloadFile(filename, response);
		out.print(form);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
