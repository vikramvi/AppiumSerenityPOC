package com.serenity.appium.poc;

import java.util.List;

import org.junit.Test;

import com.github.cukedoctor.Cukedoctor;
import com.github.cukedoctor.api.CukedoctorConverter;
import com.github.cukedoctor.api.DocumentAttributes;
import com.github.cukedoctor.config.GlobalConfig;
import com.github.cukedoctor.api.model.Feature;
import com.github.cukedoctor.parser.FeatureParser;
import com.github.cukedoctor.util.FileUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class CukeDoctor {

    @Test
    public void shouldSaveDocumentationIntoDisk(){
	try{
	List<String> pathToCucumberJsonFiles = FileUtil.findJsonFiles("target/cucumber_json/");
	List<Feature> features = FeatureParser.parse(pathToCucumberJsonFiles);
	DocumentAttributes attrs = GlobalConfig.getInstance().getDocumentAttributes();
	attrs.toc("left").backend("html5")
			.docType("book")
			.icons("font").numbered(false)
			.sourceHighlighter("coderay")
			.docTitle("Documentation Title")
		    .sectAnchors(true).sectLink(true);

	CukedoctorConverter converter = Cukedoctor.instance(features, attrs);
	converter.setFilename("target/living_documentation.adoc");

	converter.saveDocumentation();
	assertThat(FileUtil.loadFile("target/living_documentation.adoc")).exists();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
    
}
