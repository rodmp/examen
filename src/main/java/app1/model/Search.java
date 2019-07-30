package app1.model;

public class Search {

	private String search;

	public Search(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	@Override
	public String toString() {
		return "Model User: " + search;
	}
}
