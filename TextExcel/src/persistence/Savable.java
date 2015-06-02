package persistence;

public interface Savable {
	public String[] getSaveData();
	public void loadFrom(String[] saveData);
}
