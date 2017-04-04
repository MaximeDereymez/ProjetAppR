package jus.aor.mobilagent.hotel;

import jus.aor.mobilagent.kernel._Service;

public class Duration implements _Service<Long> {
    
    Long t0;

    public Duration(Object[] args){
	super();
	t0 = System.currentTimeMillis();
    }
    
    @Override
    public Long call(Object... params) throws IllegalArgumentException {
	return System.currentTimeMillis()-t0;
    }

}
