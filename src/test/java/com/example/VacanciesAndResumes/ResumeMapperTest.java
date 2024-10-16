package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@SpringBootTest
public class ResumeMapperTest {

    @Autowired
    ResumeMapper resumeMapper;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    void additionalInfoToAdditionalInfoDTO(){
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setEmploymentType("Полная занятость");
        additionalInfo.setWillingToTravel(true);
        additionalInfo.setWillingToRelocate(true);

        assertEquals(resumeMapper.additionalInfoToAdditionalInfoDTO(additionalInfo),
                new AdditionalInfoDTO(true, "Полная занятость", true));
        assertNotEquals(resumeMapper.additionalInfoToAdditionalInfoDTO(additionalInfo),
                new AdditionalInfoDTO(false, "Полная занятость", true));
        assertNotEquals(resumeMapper.additionalInfoToAdditionalInfoDTO(additionalInfo),
                new AdditionalInfoDTO(false, "Частичная занятость", true));
    }

    @Test
    void additionalInfoDTOToAdditionalInfo(){
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setEmploymentType("Полная занятость");
        additionalInfo.setWillingToTravel(true);
        additionalInfo.setWillingToRelocate(true);

        assertEquals(resumeMapper.additionalInfoDTOToAdditionalInfo(
                new AdditionalInfoDTO(true, "Полная занятость", true)),
                additionalInfo);

        assertNotEquals(resumeMapper.additionalInfoDTOToAdditionalInfo(
                new AdditionalInfoDTO(false, "Полная занятость", true)),
                additionalInfo);
        assertNotEquals(resumeMapper.additionalInfoDTOToAdditionalInfo(
                new AdditionalInfoDTO(true, "Частичная занятость", true)),
                additionalInfo);
    }

    @Test
    void candidateToCandidateDTO(){
        Candidate candidate = new Candidate();
        candidate.setLastName("Иванов");
        candidate.setFirstName("Иван");
        candidate.setMiddleName("Иванович");
        candidate.setGender(1);
        candidate.setBirthDate(LocalDate.parse("1992-10-30", dtf));
        candidate.setCountry("Россия");
        candidate.setRegion("Белгородская область");
        candidate.setCity("Белгород");
        candidate.setCitizenship("Россия");
        candidate.setHasWorkPermit(true);

        assertEquals(resumeMapper.candidateToCandidateDTO(candidate),
                new CandidateDTO("Иванов", "Иван", "Иванович", 1, "1992.10.30", "Россия",
                        "Белгородская область", "Белгород", "Россия", true)
                );
    }

    @Test
    void candidateDTOToCandidate(){
        Candidate candidate = new Candidate();
        candidate.setLastName("Иванов");
        candidate.setFirstName("Иван");
        candidate.setMiddleName("Иванович");
        candidate.setGender(1);
        candidate.setBirthDate(LocalDate.parse("1992-10-30", dtf));
        candidate.setCountry("Россия");
        candidate.setRegion("Белгородская область");
        candidate.setCity("Белгород");
        candidate.setCitizenship("Россия");
        candidate.setHasWorkPermit(true);

        assertEquals(candidate,
                resumeMapper.candidateDTOToCandidate(new CandidateDTO("Иванов", "Иван",
                        "Иванович", 1, "1992.10.30", "Россия",
                        "Белгородская область", "Белгород", "Россия", true))
        );
    }

    @Test
    void certificatesQualificationToCertificatesQualificationDTO(){
        CertificatesQualification certificatesQualification = new CertificatesQualification();
        certificatesQualification.setEducationalInstitution("Skillbox");
        certificatesQualification.setOrganization("ООО Skillbox");
        certificatesQualification.setSpecialization("Python разработка");
        certificatesQualification.setGraduationYear(2020);

        assertEquals(resumeMapper.certificatesQualificationToCertificatesQualificationDTO(certificatesQualification),
                new CertificatesQualificationDTO("Skillbox", "ООО Skillbox",
                        "Python разработка", 2020)
        );
    }

    @Test
    void certificatesQualificationDTOToCertificatesQualification(){
        CertificatesQualification certificatesQualification = new CertificatesQualification();
        certificatesQualification.setEducationalInstitution("Skillbox");
        certificatesQualification.setOrganization("ООО Skillbox");
        certificatesQualification.setSpecialization("Python разработка");
        certificatesQualification.setGraduationYear(2020);

        assertEquals(certificatesQualification,
                resumeMapper.certificatesQualificationDTOToCertificatesQualification(
                        new CertificatesQualificationDTO("Skillbox", "ООО Skillbox",
                                "Python разработка", 2020))
        );
    }

    @Test
    void contactToContactDTO(){
        Contact contact = new Contact();
        contact.setMobilePhone("7(920)999-99-99");
        contact.setEmail("ivanov@mail.ru");
        contact.setTelegram("@ivanov");
        contact.setWhatsapp("7(920)999-99-99");
        contact.setVk("vk.com/ivanov");
        contact.setHabr("habr.com/ivanov");
        contact.setLinkedIn("linkedin.com/in/ivanov");
        contact.setGitHub("github.com/ivanov");

        assertEquals(resumeMapper.contactToContactDTO(contact),
                new ContactDTO("7(920)999-99-99", "ivanov@mail.ru", "@ivanov", "7(920)999-99-99",
                        "vk.com/ivanov", "habr.com/ivanov", "linkedin.com/in/ivanov", "github.com/ivanov")
        );
    }

    @Test
    void contactDTOToContact(){
        Contact contact = new Contact();
        contact.setMobilePhone("7(920)999-99-99");
        contact.setEmail("ivanov@mail.ru");
        contact.setTelegram("@ivanov");
        contact.setWhatsapp("7(920)999-99-99");
        contact.setVk("vk.com/ivanov");
        contact.setHabr("habr.com/ivanov");
        contact.setLinkedIn("linkedin.com/in/ivanov");
        contact.setGitHub("github.com/ivanov");

        assertEquals(contact,
                resumeMapper.contactDTOToContact(new ContactDTO("7(920)999-99-99", "ivanov@mail.ru", "@ivanov",
                        "7(920)999-99-99", "vk.com/ivanov", "habr.com/ivanov", "linkedin.com/in/ivanov",
                        "github.com/ivanov"))
        );
    }

    @Test
    void documentToDocumentDTO(){
        Document document = new Document();
        document.setDocument("aaaa".getBytes());

        assertEquals(resumeMapper.documentToDocumentDTO(document),
                new DocumentDTO("aaaa")
        );
    }

    @Test
    void documentDTOToDocument(){
        Document document = new Document();
        document.setDocument("aaaa".getBytes());

        assertEquals(document,
                resumeMapper.documentDTOToDocument(new DocumentDTO("aaaa"))
        );
    }

    @Test
    void educationToEducationDTO(){
        Education education = new Education();
        education.setEducationLevel("Высшее");
        education.setInstitution("МГУ");
        education.setFaculty("Факультет ВМиК");
        education.setSpecialization("Программная инженерия");
        education.setGraduationYear(2013);

        assertEquals(resumeMapper.educationToEducationDTO(education),
                new EducationDTO("Высшее", "МГУ", "Факультет ВМиК",
                        "Программная инженерия", 2013)
        );
    }

    @Test
    void educationDTOToEducation(){
        Education education = new Education();
        education.setEducationLevel("Высшее");
        education.setInstitution("МГУ");
        education.setFaculty("Факультет ВМиК");
        education.setSpecialization("Программная инженерия");
        education.setGraduationYear(2013);

        assertEquals(education,
                resumeMapper.educationDTOToEducation(new EducationDTO("Высшее", "МГУ", "Факультет ВМиК",
                        "Программная инженерия", 2013))
        );
    }

    @Test
    void languageToLanguageDTO(){
        Language language = new Language();
        language.setLanguage("Английский");
        language.setLevel("Advanced");

        assertEquals(resumeMapper.languageToLanguageDTO(language),
                new LanguageDTO("Английский", "Advanced")
        );
    }

    @Test
    void languageDTOToLanguage(){
        Language language = new Language();
        language.setLanguage("Английский");
        language.setLevel("Advanced");

        assertEquals(language,
                resumeMapper.languageDTOToLanguage(new LanguageDTO("Английский", "Advanced"))
        );
    }

    @Test
    void resumeToResumeDTO(){
        Resume resume = new Resume();
        resume.setCandidate(new Candidate());
        resume.setContact(new Contact());
        resume.setSpecialization(new Specialization());
        resume.setWorkExperiences(List.of(new WorkExperience(), new WorkExperience()));
        resume.setLanguages(List.of(new Language(), new Language()));
        resume.setAdditionalInfo(new AdditionalInfo());
        resume.setDocuments(List.of(new Document(), new Document()));
        resume.setEducations(List.of(new Education(), new Education()));
        resume.setCertificatesQualifications(List.of(new CertificatesQualification(), new CertificatesQualification()));

        assertEquals(resumeMapper.resumeToResumeDTO(resume),
                new ResumeDTO(new CandidateDTO(), new ContactDTO(), new SpecializationDTO(), List.of(new WorkExperienceDTO(), new WorkExperienceDTO()),
                List.of(new LanguageDTO(), new LanguageDTO()), new AdditionalInfoDTO(), List.of(new DocumentDTO(), new DocumentDTO()),
                        List.of(new EducationDTO(), new EducationDTO()),
                        List.of(new CertificatesQualificationDTO(), new CertificatesQualificationDTO()))
        );
    }

    @Test
    void resumeDTOToResume(){
        Resume resume = new Resume();
        resume.setCandidate(new Candidate());
        resume.setContact(new Contact());
        resume.setSpecialization(new Specialization());
        resume.setWorkExperiences(List.of(new WorkExperience(), new WorkExperience()));
        resume.setLanguages(List.of(new Language(), new Language()));
        resume.setAdditionalInfo(new AdditionalInfo());
        resume.setDocuments(List.of(new Document(), new Document()));
        resume.setEducations(List.of(new Education(), new Education()));
        resume.setCertificatesQualifications(List.of(new CertificatesQualification(), new CertificatesQualification()));

        assertEquals(resume,
                resumeMapper.resumeDTOToResume(new ResumeDTO(new CandidateDTO(), new ContactDTO(), new SpecializationDTO(), List.of(new WorkExperienceDTO(),
                        new WorkExperienceDTO()),
                        List.of(new LanguageDTO(), new LanguageDTO()), new AdditionalInfoDTO(), List.of(new DocumentDTO(), new DocumentDTO()),
                        List.of(new EducationDTO(), new EducationDTO()),
                        List.of(new CertificatesQualificationDTO(), new CertificatesQualificationDTO())))
        );
    }

    @Test
    void specializationToSpecializationDTO(){
        Specialization specialization = new Specialization();
        specialization.setDesiredPosition("Разработчик");
        specialization.setGrade("Middle");
        specialization.setKeySkills("Python, SQL, Docker");
        specialization.setSalary(10000);
        specialization.setCurrency("RUB");

        assertEquals(resumeMapper.specializationToSpecializationDTO(specialization),
                new SpecializationDTO("Разработчик", "Middle", "Python, SQL, Docker", 10000, "RUB")
        );
    }

    @Test
    void specializationDTOToSpecialization(){
        Specialization specialization = new Specialization();
        specialization.setDesiredPosition("Разработчик");
        specialization.setGrade("Middle");
        specialization.setKeySkills("Python, SQL, Docker");
        specialization.setSalary(10000);
        specialization.setCurrency("RUB");

        assertEquals(specialization,
                resumeMapper.specializationDTOToSpecialization(new SpecializationDTO("Разработчик", "Middle",
                        "Python, SQL, Docker", 10000, "RUB"))
        );
    }

    @Test
    void workExperienceToWorkExperienceDTO(){
        WorkExperience workExperience = new WorkExperience();
        workExperience.setOrganizationName("Tech Corp");
        workExperience.setIndustry("IT");
        workExperience.setOrganizationWebsite("techcorp.com");
        workExperience.setCompanyCity("Москва");
        workExperience.setPosition("Программист");
        workExperience.setStartDate(LocalDate.parse("2015-07-15", dtf));
        workExperience.setCurrentJob(false);
        workExperience.setEndDate(LocalDate.parse("2020-08-28", dtf));
        workExperience.setAdditionalInfo("Работа в крупной компании");



        assertEquals(resumeMapper.workExperienceToWorkExperienceDTO(workExperience),
                new WorkExperienceDTO("Tech Corp", "IT", "techcorp.com", "Москва",
                        "Программист", "2015.07.15", false, "2020.08.28", "Работа в крупной компании")
        );
    }

    @Test
    void workExperienceDTOToWorkExperience(){
        WorkExperience workExperience = new WorkExperience();
        workExperience.setOrganizationName("Tech Corp");
        workExperience.setIndustry("IT");
        workExperience.setOrganizationWebsite("techcorp.com");
        workExperience.setCompanyCity("Москва");
        workExperience.setPosition("Программист");
        workExperience.setStartDate(LocalDate.parse("2015-07-15", dtf));
        workExperience.setCurrentJob(false);
        workExperience.setEndDate(LocalDate.parse("2020-08-28", dtf));
        workExperience.setAdditionalInfo("Работа в крупной компании");



        assertEquals(workExperience,
                resumeMapper.workExperienceDTOToWorkExperience(new WorkExperienceDTO("Tech Corp", "IT",
                        "techcorp.com", "Москва", "Программист", "2015.07.15",
                        false, "2020.08.28", "Работа в крупной компании"))
        );
    }
}
