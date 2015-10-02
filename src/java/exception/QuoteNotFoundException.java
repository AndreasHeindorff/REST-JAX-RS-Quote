/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Andreas
 */
public class QuoteNotFoundException extends Exception {

    public QuoteNotFoundException(String no_quote_with_that_id) {
    super(no_quote_with_that_id);
    }
    
}
