package com.weng.demo.basic.model.response;


import com.weng.demo.basic.entity.Org;
import com.weng.framework.common.model.component.tree.TreeNode;
import com.weng.framework.common.model.component.tree.TreeNodeConverter;

/**
 * 权限树列表
 * @author wengzhonghui
 */
public class OrgTreeNode extends TreeNode implements TreeNodeConverter<Org, OrgTreeNode> {

	private String id;
    private String name;
    private String code;
    private int position;
    private String sourcesName;
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSourcesName() {
        return sourcesName;
    }

    public void setSourcesName(String sourcesName) {
        this.sourcesName = sourcesName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
    public OrgTreeNode convert(Org t) {
        OrgTreeNode node = new OrgTreeNode();
        // tree node
        node.setKey(t.getId());
        node.setLabel(t.getOrgName());
        node.setParentId(t.getParentId());

        // property
        node.setId(t.getId());
        node.setName(t.getOrgName());
        node.setCode(t.getOrgTypeCode());
        return node;
    }
}
