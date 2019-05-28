package br.com.projeto.enums;


	public enum TipoEvento {
	    /* TIPO DE EVENTO - CSS */
	    CONSULTA("Consulta", ""),
	    RETORNO("Retorno", "retorno");
	    

	    private final String descricao;
	    private final String css;

	    private TipoEvento(String descricao, String css) {
	        this.css = css;
	        this.descricao = descricao;
	    }

	    public String getCss() {
	        return css;
	    }

	    public String getDescricao() {
	        return descricao;
	    }
	    public String setCss(String css) {
	    	
	        return CONSULTA.setCss("emp1") ;
	        //RETORNO.setCss("emp2");
	    	
	    	 
	    }

	}

