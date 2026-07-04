//package mpack.exceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import jakarta.validation.ConstraintViolationException;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException err){
//		
//		HashMap<String, String> map = new HashMap<>();
//		
//		err.getBindingResult().getAllErrors().forEach(a-> {
//			
//			String error = a.getDefaultMessage();
//			String field =  ((FieldError)a).getField();
//			
//			map.put(field , error);
//			
//			
//		});
//		
//		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//		
//	}
//	
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map<String, String>> handleConstraintViolation(
//            ConstraintViolationException ex) {
//        
//        Map<String, String> errors = new HashMap<>();
//        ex.getConstraintViolations().forEach(violation -> {
//            String path = violation.getPropertyPath().toString();
//            String message = violation.getMessage();
//            errors.put(path, message);
//        });
//        return ResponseEntity.badRequest().body(errors);
//    }
//	
//	@ExceptionHandler(	NoSuchElementException.class)
//	public ResponseEntity<?> handleNotfoundException (NoSuchElementException err){
//		
//		return new ResponseEntity<>("Requested Resource Not found", HttpStatus.NOT_FOUND);
//	}
//	
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<?> handleRuntimeException (RuntimeException err){
//		
//		
//		return new ResponseEntity<>(err.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
//		
//	}
//	
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleException (Exception err){
//		
//		return new ResponseEntity<>("something went wrong" , HttpStatus.INTERNAL_SERVER_ERROR);
//		
//	}
//	
//
//
//
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//}
