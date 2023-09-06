package com.sayan.BlogApplication.Services;

import com.sayan.BlogApplication.DTO.AuthorRequest;
import com.sayan.BlogApplication.DTO.AuthorResponse;
import com.sayan.BlogApplication.Model.Author;

public interface AuthorServices {
    AuthorResponse createNewAuthor(AuthorRequest author);
    AuthorResponse updateAuthor(AuthorRequest authorRequest);
    AuthorResponse viewAuthor(String id);
    String deleteAuthor(String id);

}
