package org.pharmgkb.pharmcat.haplotype;

import java.nio.file.Path;
import java.util.List;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.pharmgkb.common.util.PathUtils;
import org.pharmgkb.pharmcat.haplotype.model.Result;
import org.pharmgkb.pharmcat.util.DataManager;

import static org.pharmgkb.pharmcat.haplotype.NamedAlleleMatcherTest.*;


/**
 * JUnit test for {@link NamedAlleleMatcher#callDiplotypes(MatchData, boolean)}.
 *
 * @author Lester Carter
 */
public class NamedAlleleMatcherUgt1a1Test {
  private Path m_definitionFile;

  // TODO: lester - work in progress.  This needs checking against real world examples.

  @Before
  public void before() throws Exception {
    m_definitionFile = DataManager.DEFAULT_DEFINITION_DIR.resolve("UGT1A1_translation.json");
  }

  @Test
  public void ugt1a1s1s1() throws Exception {
    // Test *1/*1
    Path vcfFile = PathUtils.getPathToResource("org/pharmgkb/pharmcat/haplotype/UGT1A1/s1s1.vcf");
    List<String> expectedMatches = Lists.newArrayList("*1/*1");

    Result result = testMatchNamedAlleles(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }

  @Test
  public void ugt1a1s28s37() throws Exception {
    // Test *28/*37 contains TA repeat
    Path vcfFile = PathUtils.getPathToResource("org/pharmgkb/pharmcat/haplotype/UGT1A1/s28s37.vcf");
    List<String> expectedMatches = Lists.newArrayList("*28/*37");

    Result result = testMatchNamedAlleles(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }

  @Test
  public void ugt1a1s6s6() throws Exception {
    // Test *6/*6
    Path vcfFile = PathUtils.getPathToResource("org/pharmgkb/pharmcat/haplotype/UGT1A1/s6s6.vcf");
    List<String> expectedMatches = Lists.newArrayList("*6/*6");

    Result result = testMatchNamedAlleles(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }

  @Test
  public void ugt1a1s1s60s80() throws Exception {
    // report out all matching alleles and combinations of them
    // e.g. for a het *60 and *80  would be *60 + *80/*1 and *60/*80
    Path vcfFile = PathUtils.getPathToResource("org/pharmgkb/pharmcat/haplotype/UGT1A1/s1s60s80.vcf");
    List<String> expectedMatches = Lists.newArrayList("*1/*60", "*1/*80");

    Result result = testMatchNamedAlleles(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }

  @Test
  public void ugt1a1s1s28s60s80() throws Exception {
    // Example with three star alleles.  All options are output
    Path vcfFile = PathUtils.getPathToResource("org/pharmgkb/pharmcat/haplotype/UGT1A1/s1s28s60s80.vcf");
    List<String> expectedMatches = Lists.newArrayList("*1/*28", "*1/*60", "*1/*80");

    Result result = testMatchNamedAlleles(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }

  @Test
  public void ugt1a1s1s60s80phased() throws Exception {
    // Example of phased data
    Path vcfFile = PathUtils.getPathToResource("org/pharmgkb/pharmcat/haplotype/UGT1A1/s1s60s80phased.vcf");
    List<String> expectedMatches = Lists.newArrayList("*1/*60", "*1/*80");

    Result result = testMatchNamedAlleles(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);

    assertPhasedOutput("*1/*60+*80", result);
  }

  @Test
  public void ugt1a1s28s80s6s60phased() throws Exception {
    // Example of phased data with four alelles. Work in progress
    Path vcfFile = PathUtils.getPathToResource("org/pharmgkb/pharmcat/haplotype/UGT1A1/s28s80s6s60phased.vcf");
    List<String> expectedMatches = Lists.newArrayList("*28/*60", "*6/*28", "*6/*80", "*60/*80");

    Result result = testMatchNamedAlleles(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);

    assertPhasedOutput("*28+*80/*6+*60", result);
  }



}
