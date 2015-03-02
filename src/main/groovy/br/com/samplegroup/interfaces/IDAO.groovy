package br.com.samplegroup.interfaces

/**
 * Created by martins on 3/2/2015.
 */
interface IDAO {

    Object findAll()
    Object findAll(Map exactFieldsSearch)
    Object findInIndexedTexts(String search)
    Object findOne(String _id)
    Object remove(String _id)
    Object save(Object obj)

}
