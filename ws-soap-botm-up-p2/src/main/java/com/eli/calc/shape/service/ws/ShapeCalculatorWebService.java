package com.eli.calc.shape.service.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.eli.calc.shape.domain.CalculationRequest;
import com.eli.calc.shape.domain.CalculationResult;
import com.eli.calc.shape.model.CalcType;
import com.eli.calc.shape.model.ShapeName;
import com.eli.calc.shape.service.ws.types.CalculatedResultsResponse;
import com.eli.calc.shape.service.ws.types.PendingRequestsResponse;
import com.eli.calc.shape.service.ws.types.RunPendingRequestsResponse;
import com.eli.calc.shape.service.ws.types.SuccessOrErrorResponse;

@WebService
public interface ShapeCalculatorWebService {

	SuccessOrErrorResponse deleteAllPendingRequests();
	
	SuccessOrErrorResponse deleteAllResults();
	
	SuccessOrErrorResponse queueCalculationRequest(
			@XmlElement(name="ShapeName", required=true) ShapeName shapeName, 
			@XmlElement(name="CalcType", required=true) CalcType calcType, 
			@XmlElement(name="Dimension", required=true) double dimension);
	
	PendingRequestsResponse getAllPendingRequests();
	//List<CalculationRequest> getAllPendingRequests();
	
	CalculatedResultsResponse getAllCalculatedResults();
	//List<CalculationResult> getAllCalculatedResults();
	
	RunPendingRequestsResponse runAllPendingRequestsStopOnError();
	RunPendingRequestsResponse runAllPendingRequestsNoStopOnError();
	
}