package com.eli.calc.shape.service.ws.types;

public class RunPendingRequestsResponse extends SuccessOrErrorResponse {

	private int numRun;
	
	public RunPendingRequestsResponse() {
		super();
	}

	public RunPendingRequestsResponse(int numRun) {
		super(RespCode.SUCCESS,"Requests Run");
		this.numRun = numRun;
	}

	public RunPendingRequestsResponse(Exception e) {
		
		super("Error Running Requests",e);
	}

	public int getNumRun() {
		return numRun;
	}

	public void setNumRun(int numRun) {
		this.numRun = numRun;
	}

	@Override
	public String toString() {
		return "RunPendingRequestsResponse [numRun=" + numRun + "]";
	}

	
}
