public enum CategoriaEnum {
	VIP("VIP"), //0
	APARTAMENTO("APARTAMENTO"); //1

	private String descricao;

	CategoriaEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
}


