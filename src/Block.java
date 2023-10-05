
public class Block {
    private boolean isDefaultBlock;
	private int newSize;
    private int newBlockSpeed;
    private int newJumpSpeed;
    private int newGravity;
    private int newMaxVelocity;
    private int newVelocitySlowdown;
    private boolean win = false;
    
    public Block(int newSize, int newBlockSpeed, int newJumpSpeed, int newGravity, int newMaxVelocity, int newVelocitySlowdown){
    	this.newSize = newSize;
    	this.newBlockSpeed = newBlockSpeed;
    	this.newJumpSpeed = newJumpSpeed;
    	this.newGravity = newGravity;
    	this.newMaxVelocity = newMaxVelocity;
    	this.newVelocitySlowdown = newVelocitySlowdown;
    	
    	setDefaultBlock(false);
    }
    
    public Block(boolean isDefaultBlock) {
    	this.setDefaultBlock(isDefaultBlock);
    	if(isDefaultBlock == true) {
	    	this.newSize = 50;
	    	this.newBlockSpeed = 2;
	    	this.newJumpSpeed = 40;
	    	this.newGravity = 3;
	    	this.newMaxVelocity = 40;
	    	this.newVelocitySlowdown = 1;
    	}else {
    		this.newSize = 0;
	    	this.newBlockSpeed = 0;
	    	this.newJumpSpeed = 0;
	    	this.newGravity = 0;
	    	this.newMaxVelocity = 0;
	    	this.newVelocitySlowdown = 0;
    	}	
    }
    
    public Block() {
        this.setDefaultBlock(true);
    	this.newSize = 50;
    	this.newBlockSpeed = 2;
    	this.newJumpSpeed = 40;
    	this.newGravity = 3;
    	this.newMaxVelocity = 40;
    	this.newVelocitySlowdown = 1;
    }
    
	public int getNewSize() {
		return newSize;
	}
	
	public void setNewSize(int newSize) {
		this.newSize = newSize;
	}
	
	public int getNewBlockSpeed() {
		return newBlockSpeed;
	}
	
	public void setNewBlockSpeed(int newBlockSpeed) {
		this.newBlockSpeed = newBlockSpeed;
	}
	
	public int getNewJumpSpeed() {
		return newJumpSpeed;
	}
	
	public void setNewJumpSpeed(int newJumpSpeed) {
		this.newJumpSpeed = newJumpSpeed;
	}
	
	public int getNewGravity() {
		return newGravity;
	}
	
	public void setNewGravity(int newGravity) {
		this.newGravity = newGravity;
	}
	
	public int getNewMaxVelocity() {
		return newMaxVelocity;
	}
	
	public void setNewMaxVelocity(int newMaxVelocity) {
		this.newMaxVelocity = newMaxVelocity;
	}
	
	public int getNewVelocitySlowdown() {
		return newVelocitySlowdown;
	}
	
	public void setNewVelocitySlowdown(int newVelocitySlowdown) {
		this.newVelocitySlowdown = newVelocitySlowdown;
	}

	public boolean isDefaultBlock() {
		return isDefaultBlock;
	}

	public void setDefaultBlock(boolean isDefaultBlock) {
		this.isDefaultBlock = isDefaultBlock;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

}
