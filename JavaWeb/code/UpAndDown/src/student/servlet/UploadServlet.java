package student.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHARACTER = "UTF-8";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARACTER);
		response.setCharacterEncoding(CHARACTER);
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			// 上传
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

			if (isMultipart) { // 判断是否存在 Multipart 属性
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				// 设置上传文件缓冲区
				factory.setSizeThreshold(10 * 1024); // 10kb
				
				// 限制单个文件上传的大小
				upload.setSizeMax(30 * 1024); // 30KB
				
				// 将 form 表单中的所有请求字段保存到 items 集合
				List<FileItem> items = upload.parseRequest(request);
				
				Iterator<FileItem> iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					String itemName = item.getFieldName();
					int sno = -1;
					String sname = null;
					
					if (item.isFormField()) { // 判断前台字段是普通表单字段，还是 multipart 字段
						if ("sno".equals(itemName)) { // 根据 name 属性，判断 item 属于哪个字段
							sno = Integer.parseInt(item.getString(CHARACTER));
							
						} else if ("sname".equals(itemName)) {
							sname = item.getString(CHARACTER);
							
						} else {
							System.out.println("其他字段");
						}
					} else { // multipart 字段
						String fileName = item.getName(); // 文件名
						String suffix = fileName.substring(fileName.indexOf(".") + 1); // 文件名后缀
						
						if (!("png".equals(suffix) || "jpg".equals(suffix))) { // 限制上传格式
							System.out.println("格式有误！");
							return;
						}
						
						// 获取上传文件夹：upload 的路径
						String path = request.getSession().getServletContext().getRealPath("upload");
						// 指定上传位置
						File file = new File(path, fileName);
						
						item.write(file); // 上传
						
						System.out.println(fileName + "上传成功！");
						return;
					}
				}
			}
			
		} catch (FileUploadBase.SizeLimitExceededException e) {
			System.out.println("上传文件大小超出限制！最大30KB");
		} catch (FileUploadException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
