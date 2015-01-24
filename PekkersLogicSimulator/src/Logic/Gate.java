package Logic;


public interface Gate {
	public boolean update(long deltaTime);
	//public void render();
        @Override
	public String toString();
        public void setInputPin(int pos , InputPin ip);
        public InputPin getInputPin(int pos );
        public void setOutputPin(int pos , OutputPin op);
        public OutputPin getOutputPin(int pos );
}