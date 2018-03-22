package loaders;

import java.util.List;

import nodes.Node;

public abstract class Loader {
	public abstract void LoadData();
	public abstract List<Node> getNodeList();
}
