package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.DTO.AuthorRequest;
import com.sayan.BlogApplication.DTO.AuthorResponse;
import com.sayan.BlogApplication.Helper.AuthorHelper;
import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.ContentType;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import com.sayan.BlogApplication.Repository.ContentTypeRepo;
import com.sayan.BlogApplication.Services.AuthorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorServices {

    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private ContentTypeRepo contentTypeRepo;
    @Override
    public AuthorResponse createNewAuthor(AuthorRequest authorDto) {
        Author author = new Author();
        ContentType contentType = new ContentType();
        AuthorResponse authorResponse = new AuthorResponse();
        AuthorHelper.setAuthorRequest(author,contentType,authorDto);
        Author savedAuthor = authorRepo.save(author);
        ContentType savedContentType = contentTypeRepo.save(contentType);
        return AuthorHelper.setAuthorResponse(authorResponse, savedAuthor, savedContentType);
    }
    @Transactional
    @Override
    public AuthorResponse updateAuthor(AuthorRequest authorDto) {
        AuthorResponse authorResponse = new AuthorResponse();
        if(authorRepo.existsById(authorDto.getId())){

            Author fetchedAuthor = authorRepo.findByid(authorDto.getId());
            ContentType authorContent = contentTypeRepo.findByauthorId(authorDto.getId());

            fetchedAuthor.setName(authorDto.getName());
            fetchedAuthor.setUsername(authorDto.getUsername());
            fetchedAuthor.setEmail(authorDto.getEmail());
            fetchedAuthor.setPhoneNumber(authorDto.getPhoneNumber());
            authorContent.setContentType(authorDto.getContentType());

            Author updatedAuthor = authorRepo.save(fetchedAuthor);
            ContentType updatedAuthorContent = contentTypeRepo.save(authorContent);

            return AuthorHelper.setAuthorResponse(authorResponse,updatedAuthor,updatedAuthorContent);
        }
        throw new RuntimeException("update request has not been fulfilled due to wrong id");
    }
    @Transactional
    @Override
    public AuthorResponse viewAuthor(String id) {
        if(authorRepo.existsById(id)){
            AuthorResponse authorResponse = new AuthorResponse();
            Author author = authorRepo.findByid(id);
            ContentType authorContent = contentTypeRepo.findByauthorId(id);
            return AuthorHelper.setAuthorResponse(authorResponse,author, authorContent);

        }
        throw new RuntimeException("user not exists");

    }
    @Transactional
    @Override
    public String deleteAuthor(String id) {
        contentTypeRepo.deleteByauthorId(id);
        authorRepo.deleteById(id);
        return id + " account has been deleted !";
    }
}
