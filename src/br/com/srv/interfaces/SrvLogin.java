package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;


@Service
public interface SrvLogin extends Serializable {
	
	public boolean autentico(String login, String senha) throws Exception;
	
	
}
