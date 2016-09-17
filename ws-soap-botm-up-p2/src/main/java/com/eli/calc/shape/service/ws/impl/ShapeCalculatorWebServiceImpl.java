package com.eli.calc.shape.service.ws.impl;

import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.eli.calc.shape.domain.CalculationRequest;
import com.eli.calc.shape.domain.CalculationResult;
import com.eli.calc.shape.model.CalcType;
import com.eli.calc.shape.model.ShapeName;
import com.eli.calc.shape.service.ShapeCalculatorService;
import com.eli.calc.shape.service.ws.ShapeCalculatorWebService;
import com.eli.calc.shape.service.ws.types.CalculatedResultsResponse;
import com.eli.calc.shape.service.ws.types.PendingRequestsResponse;
import com.eli.calc.shape.service.ws.types.RespCode;
import com.eli.calc.shape.service.ws.types.SuccessOrErrorResponse;
import com.eli.calc.shape.service.ws.types.RunPendingRequestsResponse;

@WebService
public class ShapeCalculatorWebServiceImpl implements ShapeCalculatorWebService {


	private static final Logger logger = LoggerFactory.getLogger(ShapeCalculatorWebServiceImpl.class);

	@Autowired
	private ShapeCalculatorService calculator;
	
	public ShapeCalculatorWebServiceImpl() {
		logger.debug("\n\n\nConstructor\n\n\n");
	}

	@Override
	public SuccessOrErrorResponse deleteAllPendingRequests() {

		logger.debug("\n\n\nExecuting operation deleteAllPendingRequests...\n\n\n");

		SuccessOrErrorResponse response = null;
		try {
			
			calculator.deleteAllPendingRequests();
			response = new SuccessOrErrorResponse(RespCode.SUCCESS,"Requests Deleted");

		} catch (Exception e) {
			logger.error("Error attempting to deleteAllResults",e);
			response = new SuccessOrErrorResponse("Error Attempting to Delete Requests",e);
		}
		
		return response;
	}

	@Override
	public SuccessOrErrorResponse deleteAllResults() {

		logger.debug("\n\n\nExecuting operation deleteAllResults...\n\n\n");

		SuccessOrErrorResponse response = null;
		try {
			
			calculator.deleteAllResults();
			response = new SuccessOrErrorResponse(RespCode.SUCCESS,"Results Deleted");

		} catch (Exception e) {
			logger.error("Error attempting to deleteAllResults",e);
			response = new SuccessOrErrorResponse("Error Attempting to Delete Results",e);
		}
		
		return response;
	}

	@Override
	public SuccessOrErrorResponse queueCalculationRequest(
			ShapeName shapeName, 
			CalcType calcType, 
			double dimension) {

		logger.debug("\n\n\nExecuting operation queueCalculationRequest:"
				+shapeName+","+calcType+","+dimension+" ...\n\n\n");

		SuccessOrErrorResponse response = null;
		try {
			
			calculator.queueCalculationRequest(shapeName,calcType,dimension);
			response = new SuccessOrErrorResponse(RespCode.SUCCESS,"Request queued:"+shapeName+","+calcType+","+dimension);

		} catch (Exception e) {
			logger.error("Error attempting to queueCalculationRequest",e);
			response = new SuccessOrErrorResponse("Error Attempting to Queue Calculation Request",e);
		}
		
		return response;
	}

	@Override
	public PendingRequestsResponse getAllPendingRequests() {

		logger.debug("\n\n\nExecuting operation getAllPendingRequests...\n\n\n");

		PendingRequestsResponse response = null;
		try {
			
			List<CalculationRequest> list = calculator.getAllPendingRequests();
			response = new PendingRequestsResponse(list);

		} catch (Exception e) {
			logger.error("Error attempting to getAllPendingRequests",e);
			response = new PendingRequestsResponse(e);
		}
		
		return response;
	}

	@Override
	public CalculatedResultsResponse getAllCalculatedResults() {

		logger.debug("\n\n\nExecuting operation getAllCalculatedResults...\n\n\n");

		CalculatedResultsResponse response = null;
		try {
			
			List<CalculationResult> list = calculator.getAllCalculatedResults();
			response = new CalculatedResultsResponse(list);

		} catch (Exception e) {
			logger.error("Error attempting to getAllCalculatedResults",e);
			response = new CalculatedResultsResponse(e);
		}
		
		return response;
	}

	@Override
	public RunPendingRequestsResponse runAllPendingRequestsStopOnError() {

		logger.debug("\n\n\nExecuting operation runAllPendingRequestsStopOnError...\n\n\n");

		RunPendingRequestsResponse response = null;
		try {
			
			int numRun = calculator.runAllPendingRequestsStopOnError();
			response = new RunPendingRequestsResponse(numRun);

		} catch (Exception e) {
			logger.error("Error attempting to runAllPendingRequestsStopOnError",e);
			response = new RunPendingRequestsResponse(e);
		}
		
		return response;
	}

	@Override
	public RunPendingRequestsResponse runAllPendingRequestsNoStopOnError() {

		logger.debug("\n\n\nExecuting operation runAllPendingRequestsNoStopOnError ...\n\n\n");

		RunPendingRequestsResponse response = null;
		try {
			
			int numRun = calculator.runAllPendingRequestsNoStopOnError();
			response = new RunPendingRequestsResponse(numRun);

		} catch (Exception e) {
			logger.error("Error attempting to runAllPendingRequestsNoStopOnError",e);
			response = new RunPendingRequestsResponse(e);
		}
		
		return response;
	}

}
