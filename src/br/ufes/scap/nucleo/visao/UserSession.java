package br.ufes.scap.nucleo.visao;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class UserSession extends WebSession {

	private static final long serialVersionUID = 1L;

	public UserSession(Request request) {
		super(request);
	}
	
	public static UserSession getInstance(){
		return (UserSession)Session.get();
	}
	
}