package com.edu.ctu.thesis.seafood.sonhatkynuoitom;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edu.ctu.thesis.seafood.ketquathuhoach.KetQuaThuHoach;
import com.edu.ctu.thesis.validity.Validity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "so_nhat_ky_nuoi_tom")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SoNhatKyNuoiTom extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ao_nuoi")
    private String aoNuoi;

    @Column(name = "vu_nuoi")
    private String vuNuoi;

    @Column(name = "nam_nuoi")
    private LocalDate namNuoi;

    @OneToMany(mappedBy = "soNhatKyNuoiTom", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<KetQuaThuHoach> ketQuaThuHoachs;

}
