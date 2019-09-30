package org.carly.shared.utils;

public interface MapperService<T, T1> {

    T mapFromDomainObject(T1 domain, T rest);

    T1 mapToDomainObject(T1 domain, T rest);

    T simplifyRestObject(T1 domain);

    T1 simplifyDomainObject(T rest);


}