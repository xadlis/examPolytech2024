package com.intech.hero;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.BlockProcessor;
import org.asciidoctor.extension.Reader;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizJdkEngine;

public class DotBlockProcessor extends BlockProcessor {

	@Override
	public Object process(StructuralNode parent, Reader reader, Map<String, Object> attributes) {
		String content = reader.read();

		String filename = "images/dot-" + UUID.randomUUID() + ".png";
		try {
			File file = new File(System.getenv("MAVEN_PROJECTBASEDIR") + "/target/generated-docs/" + filename);
			System.out.println(file.getAbsolutePath());
			Graphviz.useEngine(new GraphvizJdkEngine());
			Graphviz.fromString(content).render(Format.PNG).toFile(
					file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return createBlock(parent, "pass",
				"<img src=\"./" + filename + "\" alt=\"" + filename + "\" >",
				attributes);
	}

}
