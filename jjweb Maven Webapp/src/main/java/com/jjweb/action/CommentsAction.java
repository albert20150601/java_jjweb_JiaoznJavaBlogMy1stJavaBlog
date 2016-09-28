package com.jjweb.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.jjweb.model.Artical;
import com.jjweb.model.Comments;
import com.jjweb.model.CommentsDTO;
import com.jjweb.model.User;
import com.jjweb.service.ArticalService;
import com.jjweb.service.CommentsService;
import com.jjweb.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class CommentsAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {
	@Resource
	private CommentsService commentsService;
	private List<Comments> listComments;
	private List<CommentsDTO> listCommentsDTO = new ArrayList<CommentsDTO>();//
	@Resource
	private ArticalService articalService;
	@Resource
	private UserService userService;
	private Artical articalTemp;

	private HttpServletRequest request;
	private ServletContext servletContext;
	private HttpServletResponse response;

	private Comments comments;
	private CommentsDTO commentsDTO;
	//private CommentsDTO commentsDTOTemp;
	@Action(value = "comments_showAll", results = { 
			@Result(name = "success", location = "/WEB-INF/content/service_comments_showAll.jsp") })
	public String execute() {
		//listComments = new ArrayList<Comments>();
		listComments = commentsService.findAll();
	//	listCommentsDTO = new ArrayList<CommentsDTO>();
		
		
		for (int i = 0; i < listComments.size(); i++) {
			Comments commentsTemp = new Comments();
			CommentsDTO commentsDTOTemp = new CommentsDTO();
			commentsTemp = listComments.get(i);
			commentsDTOTemp.setId(commentsTemp.getId());
			if (commentsTemp.getArticalid() != 0) {
				commentsDTOTemp.setArtical(articalService.findById(commentsTemp
						.getArticalid()));
			} else {
				articalTemp = new Artical();
				articalTemp.setId(0);
				articalTemp.setTitle("��ҳ��");
				commentsDTOTemp.setArtical(articalTemp);
			}

			// commentsDTOTemp.setArtical(articalService.findById(commentsTemp.getArticalid()));
			commentsDTOTemp.setContent(commentsTemp.getContent());
			commentsDTOTemp.setTime(commentsTemp.getTime());
			commentsDTOTemp.setUser(userService.findById(commentsTemp
					.getUserid()));
			listCommentsDTO.add(commentsDTOTemp);
		}

		return SUCCESS;
	}

	// @Action(value="comments_show",results={
	// @Result(name="success",location =
	// "/WEB-INF/content/study_artical_show.jsp")})
	// public String artical_show(){
	// System.out.println("artical_show action.if request exists:"+request.toString());
	//
	// artical=articalService.findById(artical.getId());
	// int
	// access=Integer.parseInt(artical.getAccess()==null?"0":artical.getAccess());
	// artical.setAccess(String.valueOf(++access));
	// articalService.merge(artical);
	//
	// String ip=request.getRemoteAddr()==null?"����ʧ��":request.getRemoteAddr();
	// //request.getRequestURI()
	// String
	// destination=request.getRequestURI()==null?"����ʧ��":request.getRequestURI();
	// String aintroduction=artical.getId()+" -- "+artical.getTitle();
	//
	// accessRecord=new AccessRecord();
	// accessRecord.setIp(ip);
	// accessRecord.setDestination(destination);
	// accessRecord.setTime(new Timestamp(new Date().getTime()));
	// accessRecordDAO.save(accessRecord);
	//
	// return SUCCESS;
	// }
	// @Action(value="comments_add",results={
	// @Result(name="input",location =
	// "/WEB-INF/content/service_comments_add.jsp")})
	// public String comments_add(){
	// categories=categoryService.findAll();
	// return INPUT;
	// }
	@Action(value = "comments_addCommit", results = { 
			@Result(name = "input", location = "/WEB-INF/content/service_comments_addCommit.jsp") })
	public String comments_addCommit() {
		comments.setTime(new Timestamp(new Date().getTime()));
		comments.setArticalid(comments.getArticalid());
		commentsService.save(comments);
		//listComments = new ArrayList<Comments>();
		listComments = commentsService.findAll();
		
		
		for (int i = 0; i < listComments.size(); i++) {
			Comments commentsTemp = new Comments();
			CommentsDTO commentsDTOTemp = new CommentsDTO();
			commentsTemp = listComments.get(i);
			commentsDTOTemp.setId(commentsTemp.getId());
			if (commentsTemp.getArticalid() != 0) {
				commentsDTOTemp.setArtical(articalService.findById(commentsTemp
						.getArticalid()));
			} else {
				articalTemp = new Artical();
				articalTemp.setId(0);
				articalTemp.setTitle("��ҳ��");
				commentsDTOTemp.setArtical(articalTemp);
			}
			commentsDTOTemp.setContent(commentsTemp.getContent());
			commentsDTOTemp.setTime(commentsTemp.getTime());
			if (commentsTemp.getUserid() != 0) {
				commentsDTOTemp.setUser(userService.findById(commentsTemp
						.getUserid()));
			} else {
				User userTemp = new User();
				userTemp.setId(0);
				userTemp.setName("�ο�");
				commentsDTOTemp.setUser(userTemp);
			}
			listCommentsDTO.add(commentsDTOTemp);
			System.out.println("-----22listCommentsDTO��һ��temp"
					+ commentsDTOTemp.getContent());//--------------------
		
		}

		// test for 2
		for (int jiaozn = 0; jiaozn < listCommentsDTO.size(); jiaozn++) {
			System.out.println("-----��KlistCommentsDTO" + jiaozn
					+ listCommentsDTO.get(jiaozn).getContent());// --------------------------------
		}
		 //test for 2
		
		return INPUT;
	}

	@Action(value = "comments_delete", results = { @Result(name = "success", location = "/WEB-INF/content/service_comments_showAll.jsp") })
	public String comments_delete() {
		comments = commentsService.findById(commentsDTO.getId());
		commentsService.delete(comments);

		listComments = new ArrayList<Comments>();
		listComments = commentsService.findAll();
//		listCommentsDTO = new ArrayList<CommentsDTO>();
		
		
		for (int i = 0; i < listComments.size(); i++) {
			Comments commentsTemp = new Comments();
			CommentsDTO commentsDTOTemp=new CommentsDTO();
			commentsTemp = listComments.get(i);
			commentsDTOTemp.setId(commentsTemp.getId());
			if (commentsTemp.getArticalid() != 0) {
				commentsDTOTemp.setArtical(articalService.findById(commentsTemp
						.getArticalid()));
			} else {
				articalTemp = new Artical();
				articalTemp.setId(0);
				articalTemp.setTitle("��ҳ��");
				commentsDTOTemp.setArtical(articalTemp);
			}
			commentsDTOTemp.setContent(commentsTemp.getContent());
			commentsDTOTemp.setTime(commentsTemp.getTime());
			commentsDTOTemp.setUser(userService.findById(commentsTemp
					.getUserid()));
			listCommentsDTO.add(commentsDTOTemp);
		}

		return SUCCESS;
	}

	@Action(value = "comments_edit", results = {
			@Result(name = "success", location = "/WEB-INF/content/service_comments_edit.jsp") })
	public String comments_edit() {
		// Comments commentsTemp=new Comments();
		// CommentsDTO commentsDTOTemp=new CommentsDTO();
		comments = commentsService.findById(commentsDTO.getId());
		commentsDTO.setId(comments.getId());
		if (comments.getArticalid() != 0) {
			commentsDTO.setArtical(articalService.findById(comments
					.getArticalid()));
		} else {
			articalTemp = new Artical();
			articalTemp.setId(0);
			articalTemp.setTitle("��ҳ��");
			commentsDTO.setArtical(articalTemp);
		}
		// commentsDTO.setArtical(articalService.findById(commentsTemp.getArticalid()));
		commentsDTO.setContent(comments.getContent());
		commentsDTO.setTime(comments.getTime());
		if (comments.getUserid() != 0) {
			commentsDTO.setUser(userService.findById(comments
					.getUserid()));
		} else {
			User userTemp = new User();
			userTemp.setId(0);
			userTemp.setName("�ο�");
			commentsDTO.setUser(userTemp);
		}
		//commentsDTO.setUser(userService.findById(comments.getUserid()));
		// commentsDTO=commentsDTOTemp;
		return SUCCESS;
	}

	@Action(value = "comments_editCommit", results = {
			@Result(name = "input", location = "/WEB-INF/content/service_comments_editCommit.jsp") })
	public String comments_editCommit() {
		comments.setTime(new Timestamp(new Date().getTime()));
		commentsService.merge(comments);
		return INPUT;
	}

	// public AccessRecord getAccessRecord() {
	// return accessRecord;
	// }
	// public void setAccessRecord(AccessRecord accessRecord) {
	// this.accessRecord = accessRecord;
	// }
	// public AccessRecordDAO getAccessRecordDAO() {
	// return accessRecordDAO;
	// }
	// public void setAccessRecordDAO(AccessRecordDAO accessRecordDAO) {
	// this.accessRecordDAO = accessRecordDAO;
	// }

	public void setServletRequest(HttpServletRequest req) {

		this.request = req;

	}

	public void setServletResponse(HttpServletResponse res) {

		this.response = res;

	}

	public void setServletContext(ServletContext ser) {

		this.servletContext = ser;

	}

	public CommentsService getCommentsService() {
		return commentsService;
	}

	public void setCommentsService(CommentsService commentsService) {
		this.commentsService = commentsService;
	}

	public List<Comments> getListComments() {
		return listComments;
	}

	public void setListComments(List<Comments> listComments) {
		this.listComments = listComments;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public List<CommentsDTO> getListCommentsDTO() {
		return listCommentsDTO;
	}

	public void setListCommentsDTO(List<CommentsDTO> listCommentsDTO) {
		this.listCommentsDTO = listCommentsDTO;
	}

	public CommentsDTO getCommentsDTO() {
		return commentsDTO;
	}

	public void setCommentsDTO(CommentsDTO commentsDTO) {
		this.commentsDTO = commentsDTO;
	}

	public ArticalService getArticalService() {
		return articalService;
	}

	public void setArticalService(ArticalService articalService) {
		this.articalService = articalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Artical getArticalTemp() {
		return articalTemp;
	}

	public void setArticalTemp(Artical articalTemp) {
		this.articalTemp = articalTemp;
	}
}
