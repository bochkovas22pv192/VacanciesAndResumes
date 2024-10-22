package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
public class ResumeMapperTest {

    @Autowired
    ResumeMapper resumeMapper;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        candidate.setRelocate(1);
        candidate.setTravel(1);
        candidate.setEmployments(Set.of(Employment.builder().employmentName("Полная занятость").build()));
        candidate.setKeySkills(Set.of(KeySkill.builder().keySkillName("Python").build()));

        assertEquals(resumeMapper.candidateToCandidateDTO(candidate),
                new CandidateDTO("Иванов", "Иван", "Иванович", 1, "1992-10-30", "Россия",
                        "Белгородская область", "Белгород", "Россия", true,
                        1, 1, "Полная занятость", "Python")
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
        candidate.setRelocate(1);
        candidate.setTravel(1);
        candidate.setEmployments(Set.of(Employment.builder().employmentName("Полная занятость").build()));
        candidate.setKeySkills(Set.of(KeySkill.builder().keySkillName("Python").build()));

        assertEquals(candidate,
                resumeMapper.candidateDTOToCandidate(new CandidateDTO("Иванов", "Иван",
                        "Иванович", 1, "1992-10-30", "Россия",
                        "Белгородская область", "Белгород", "Россия", true,
                        1, 1, "Полная занятость", "Python"))
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
        contact.setLinkedin("linkedin.com/in/ivanov");
        contact.setGithub("github.com/ivanov");

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
        contact.setLinkedin("linkedin.com/in/ivanov");
        contact.setGithub("github.com/ivanov");

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
        resume.setDocuments(List.of(new Document(), new Document()));
        resume.setEducations(List.of(new Education(), new Education()));
        resume.setCertificatesQualifications(List.of(new CertificatesQualification(), new CertificatesQualification()));

        assertEquals(resumeMapper.resumeToResumeDTO(resume),
                new ResumeDTO(new CandidateDTO(), new ContactDTO(), new SpecializationDTO(), List.of(new WorkExperienceDTO(), new WorkExperienceDTO()),
                List.of(new LanguageDTO(), new LanguageDTO()), List.of(new DocumentDTO(), new DocumentDTO()),
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
        resume.setDocuments(List.of(new Document(), new Document()));
        resume.setEducations(List.of(new Education(), new Education()));
        resume.setCertificatesQualifications(List.of(new CertificatesQualification(), new CertificatesQualification()));

        assertEquals(resume,
                resumeMapper.resumeDTOToResume(new ResumeDTO(new CandidateDTO(), new ContactDTO(), new SpecializationDTO(), List.of(new WorkExperienceDTO(),
                        new WorkExperienceDTO()),
                        List.of(new LanguageDTO(), new LanguageDTO()), List.of(new DocumentDTO(), new DocumentDTO()),
                        List.of(new EducationDTO(), new EducationDTO()),
                        List.of(new CertificatesQualificationDTO(), new CertificatesQualificationDTO())))
        );
    }

    @Test
    void specializationToSpecializationDTO(){
        Specialization specialization = new Specialization();
        specialization.setRoleName("Разработчик");
        specialization.setGrade("Middle");
        specialization.setSalary(10000);
        specialization.setCurrency("RUB");


        assertEquals(resumeMapper.specializationToSpecializationDTO(specialization),
                new SpecializationDTO("Разработчик", "Middle", 10000, "RUB")
        );
    }

    @Test
    void specializationDTOToSpecialization(){
        Specialization specialization = new Specialization();
        specialization.setRoleName("Разработчик");
        specialization.setGrade("Middle");
        specialization.setSalary(10000);
        specialization.setCurrency("RUB");

        assertEquals(specialization,
                resumeMapper.specializationDTOToSpecialization(new SpecializationDTO("Разработчик", "Middle",
                        10000, "RUB"))
        );
    }

    @Test
    void workExperienceToWorkExperienceDTO(){
        WorkExperience workExperience = new WorkExperience();
        workExperience.setOrganizationName("Tech Corp");
        workExperience.setIndustry("IT");
        workExperience.setWebsite("techcorp.com");
        workExperience.setCity("Москва");
        workExperience.setRoleName("Программист");
        workExperience.setStartDate(LocalDate.parse("2015-07-15", dtf));
        workExperience.setCurrentJob(false);
        workExperience.setEndDate(LocalDate.parse("2020-08-28", dtf));
        workExperience.setAdditionalInfo("Работа в крупной компании");



        assertEquals(resumeMapper.workExperienceToWorkExperienceDTO(workExperience),
                new WorkExperienceDTO("Tech Corp", "IT", "techcorp.com", "Москва",
                        "Программист", "2015-07-15", false, "2020-08-28", "Работа в крупной компании")
        );
    }

    @Test
    void workExperienceDTOToWorkExperience(){
        WorkExperience workExperience = new WorkExperience();
        workExperience.setOrganizationName("Tech Corp");
        workExperience.setIndustry("IT");
        workExperience.setWebsite("techcorp.com");
        workExperience.setCity("Москва");
        workExperience.setRoleName("Программист");
        workExperience.setStartDate(LocalDate.parse("2015-07-15", dtf));
        workExperience.setCurrentJob(false);
        workExperience.setEndDate(LocalDate.parse("2020-08-28", dtf));
        workExperience.setAdditionalInfo("Работа в крупной компании");



        assertEquals(workExperience,
                resumeMapper.workExperienceDTOToWorkExperience(new WorkExperienceDTO("Tech Corp", "IT",
                        "techcorp.com", "Москва", "Программист", "2015-07-15",
                        false, "2020-08-28", "Работа в крупной компании"))
        );
    }

    @Test
    void stringToKeySkill(){
        Set<KeySkill> keySkills = Set.of(KeySkill.builder().keySkillName("Python").build(), KeySkill.builder().keySkillName("C++").build());
        assertEquals(keySkills,
                    resumeMapper.stringToKeySkill("Python, C++" )
        );
    }

    @Test
    void KeySkillToString(){
        Set<KeySkill> keySkills = Set.of(KeySkill.builder().keySkillName("Python").build());
        assertEquals(resumeMapper.KeySkillToString(keySkills),
                "Python"
        );
    }

    @Test
    void stringToEmployment(){
        Set<Employment> employments = Set.of(Employment.builder().employmentName("Полная занятость").build(),
                Employment.builder().employmentName("Частичная занятость").build());
        assertEquals(employments,
                resumeMapper.stringToEmployment("Полная занятость, Частичная занятость")
        );
    }

    @Test
    void EmploymentToString(){
        Set<Employment> employments = Set.of(Employment.builder().employmentName("Полная занятость").build());
        assertEquals(resumeMapper.EmploymentToString(employments),
               "Полная занятость"
        );
    }
}
