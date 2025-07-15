package fon.e_dnevnik.attendanceservice.service;

import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import fon.e_dnevnik.attendanceservice.dao.AbsenceRepository;
import fon.e_dnevnik.attendanceservice.dto.AbsenceDTO;
import fon.e_dnevnik.attendanceservice.entity.Absence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceImplementation  {

    private AbsenceRepository absenceRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AbsenceImplementation(AbsenceRepository absenceRepository, ModelMapper modelMapper) {
        this.absenceRepository = absenceRepository;
        this.modelMapper = modelMapper;
    }
    public AbsenceDTO save(AbsenceDTO absenceDTO) throws Exception {
        System.out.println("Received AbsenceDTO: " + absenceDTO);
        Absence absence = new Absence();
        AbsencePK id = new AbsencePK(
                absenceDTO.getTeacherusername(),
                absenceDTO.getClassid(),
                absenceDTO.getStudentusername(),
                absenceDTO.getLessonid()
        );
        absence.setId(id);
        modelMapper.map(absenceDTO, absence);
        Absence savedAbsence = absenceRepository.save(absence);
        AbsenceDTO savedDTO = new AbsenceDTO();
        savedDTO.setTeacherusername(savedAbsence.getId().getTeacherusername());
        savedDTO.setClassid(savedAbsence.getId().getClassid());
        savedDTO.setStudentusername(savedAbsence.getId().getStudentusername());
        savedDTO.setLessonid(savedAbsence.getId().getLessonid());
        savedDTO.setExcused(savedAbsence.isExcused());
        savedDTO.setIsfinal(savedAbsence.isIsfinal());
        return savedDTO;
        //return modelMapper.map(savedAbsence, AbsenceDTO.class);
    }
/*
    public List<AbsenceDTO> findByIdStudentusername(String username){
        List<Absence> absences = absenceRepository.findByIdStudentusername(username);
        List<AbsenceDTO> absenceDTOS = new ArrayList<>();
        for(Absence absence :absences){
            AbsenceDTO absenceDTO = modelMapper.map(absence, AbsenceDTO.class);
            absenceDTOS.add(absenceDTO);
        }
        return absenceDTOS;
    }
*/
public List<AbsenceDTO> findByIdStudentusername(String username){
    List<Absence> absences = absenceRepository.findByIdStudentusername(username);
    List<AbsenceDTO> absenceDTOS = new ArrayList<>();
    for(Absence absence : absences){
        AbsenceDTO absenceDTO = new AbsenceDTO();
        absenceDTO.setTeacherusername(absence.getId().getTeacherusername());
        absenceDTO.setClassid(absence.getId().getClassid());
        absenceDTO.setStudentusername(absence.getId().getStudentusername());
        absenceDTO.setLessonid(absence.getId().getLessonid());
        absenceDTO.setExcused(absence.isExcused());
        absenceDTO.setIsfinal(absence.isIsfinal());
        absenceDTOS.add(absenceDTO);
    }
    return absenceDTOS;
}

    public AbsenceDTO modify(AbsencePK id, boolean excused, boolean isFinal) throws Exception {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            Absence absence = optionalAbsence.get();
            System.out.println(absence.getId().getTeacherusername());
            absence.setExcused(excused);
            System.out.println(excused);
            absence.setIsfinal(isFinal);
            Absence updatedAbsence = absenceRepository.save(absence);
            return modelMapper.map(updatedAbsence, AbsenceDTO.class);
        } else {
            throw new Exception("Ne postoji odsustvo sa zadatim ID-jem");
        }
    }
}
