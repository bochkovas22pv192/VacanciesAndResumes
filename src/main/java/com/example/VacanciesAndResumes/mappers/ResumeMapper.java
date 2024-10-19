package com.example.VacanciesAndResumes.mappers;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResumeMapper {
    ResumeDTO resumeToResumeDTO(Resume entity);
    Resume resumeDTOToResume(ResumeDTO entity);

    List<ResumeDTO> resumeToResumeDTO(List<Resume> entity);
    List<Resume> resumeDTOToResume(List<ResumeDTO> entity);

    @Mapping(target="birthDate", source="entity.birthDate", dateFormat = "yyyy-MM-dd")
    CandidateDTO candidateToCandidateDTO(Candidate entity);
    @Mapping(target="birthDate", source="entity.birthDate", dateFormat = "yyyy-MM-dd")
    Candidate candidateDTOToCandidate(CandidateDTO entity);

    CertificatesQualificationDTO certificatesQualificationToCertificatesQualificationDTO(CertificatesQualification entity);
    CertificatesQualification certificatesQualificationDTOToCertificatesQualification(CertificatesQualificationDTO entity);
    List<CertificatesQualificationDTO> certificatesQualificationToCertificatesQualificationDTO(List<CertificatesQualification> entity);
    List<CertificatesQualification> certificatesQualificationDTOToCertificatesQualification(List<CertificatesQualificationDTO> entity);

    ContactDTO contactToContactDTO(Contact entity);
    Contact contactDTOToContact(ContactDTO entity);

    DocumentDTO documentToDocumentDTO(Document entity);
    Document documentDTOToDocument(DocumentDTO entity);

    List<DocumentDTO> documentToDocumentDTO(List<Document> entity);
    List<Document> documentDTOToDocument(List<DocumentDTO> entity);

    EducationDTO educationToEducationDTO(Education entity);
    Education educationDTOToEducation(EducationDTO entity);
    List<EducationDTO> educationToEducationDTO(List<Education> entity);
    List<Education> educationDTOToEducation(List<EducationDTO> entity);

    LanguageDTO languageToLanguageDTO(Language entity);
    Language languageDTOToLanguage(LanguageDTO entity);

    List<LanguageDTO> languageToLanguageDTO(List<Language>  entity);
    List<Language> languageDTOToLanguage(List<LanguageDTO> entity);

    SpecializationDTO specializationToSpecializationDTO(Specialization entity);
    Specialization specializationDTOToSpecialization(SpecializationDTO entity);

    @Mapping(target="startDate", source="entity.startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target="endDate", source="entity.endDate", dateFormat = "yyyy-MM-dd")
    WorkExperienceDTO workExperienceToWorkExperienceDTO(WorkExperience entity);
    @Mapping(target="startDate", source="entity.startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target="endDate", source="entity.endDate", dateFormat = "yyyy-MM-dd")
    WorkExperience workExperienceDTOToWorkExperience(WorkExperienceDTO entity);

    @Mapping(target="startDate", source="entity.startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target="endDate", source="entity.endDate", dateFormat = "yyyy-MM-dd")
    List<WorkExperienceDTO> workExperienceToWorkExperienceDTO(List<WorkExperience> entity);
    @Mapping(target="startDate", source="entity.startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target="endDate", source="entity.endDate", dateFormat = "yyyy-MM-dd")
    List<WorkExperience> workExperienceDTOToWorkExperience(List<WorkExperienceDTO> entity);

    EmploymentDTO employmentToEmploymentDTO(Employment entity);
    Employment employmentDTOToEmployment(EmploymentDTO entity);

    Set<EmploymentDTO> employmentToEmploymentDTO(Set<Employment> entity);
    Set<Employment> employmentDTOToEmployment(Set<EmploymentDTO> entity);

    KeySkillDTO keySkillToKeySkillDTO(KeySkill entity);
    KeySkill keySkillDTOToKeySkill(KeySkillDTO entity);

    Set<KeySkillDTO> keySkillToKeySkillDTO(Set<KeySkill> entity);
    Set<KeySkill> keySkillDTOToKeySkill(Set<KeySkillDTO> entity);


    default byte[] stringToBytes(String string) {
        return string != null ? string.getBytes() : null;
    }
    default String bytesToString(byte[] bytes) {
        if (bytes != null){
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return null;
    }
}
