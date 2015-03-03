package br.com.samplegroup.interfaces

import br.com.samplegroup.exceptions.ValidationException

/**
 * Created by 9994835 on 19/01/2015.
 */
interface IValidator {

    void validate(Object object) throws ValidationException

}
