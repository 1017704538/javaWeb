function getFolder(driver) {

	fso = new ActiveXObject("Scripting.FileSystemObject");

	var eSubFolders = new Enumerator(fso.GetFolder(driver).SubFolders); // 获取子目录

	var eFiles = new Enumerator(fso.GetFolder(driver).files); // 获取文件

	var itemSubFolder = "";

	var i = 0;

	var table = "<table border='1' width='97%'>";

	if (!fso.GetFolder(driver.replace(new RegExp("\\\\", "g"), "\\\\")).IsRootFolder) { // 判断是否为根目

		var fatherDriver1 = new String(fso.GetFolder(driver).ParentFolder);

		var fatherDriver = fatherDriver1.replace(new RegExp("\\\\", "g"),
				"\\\\");

		var tr = "<tr><td height='27px'><a href='#' onclick='getFolder(\""
				+ fatherDriver + "\");'>返回上一级</a></td></tr>";

	} else {

		var tr = "";

	}

	for (; !eSubFolders.atEnd(); eSubFolders.moveNext()) {

		itemSubFolder = eSubFolders.item(); // 获取其中的一个目录

		var eSubFolder_son = new Enumerator(
				fso.GetFolder(itemSubFolder).SubFolders); // 获取子目录的子目录

		/** *********************判断是否包含图片文件************************************** */

		var eSubFolder_file = new Enumerator(fso.GetFolder(itemSubFolder).files); // 获取子目录包含的文件

		for (; !eSubFolder_file.atEnd(); eSubFolder_file.moveNext()) {

			var itemSubFolder_file = eSubFolder_file.item();

			var flag = false; // 是否包含图片文件的标记变量

			if (itemSubFolder_file.Type.indexOf("JPG") != -1
					|| itemSubFolder_file.Type.indexOf("JPEG") != -1

					|| itemSubFolder_file.Type.indexOf("GIF") != -1) {

				flag = true;

				break; // 跳出for循环

			}

		}

		/** **************************************************************************** */

		if (!eSubFolder_son.atEnd() || flag) { // 包含图片文件或是子文件夹时显示超链接

			var str_itemSubFolder = new String(itemSubFolder);

			itemSubFolder_ = str_itemSubFolder.replace(new RegExp("\\\\", "g"),
					"\\\\");

			tr = tr + "<tr><td><a href='#' onclick='getFolder(\""
					+ itemSubFolder_ + "\")'>" + itemSubFolder
					+ "</a></td></tr>";

		} else {

			tr = tr + "<tr><td>" + itemSubFolder + "</td></tr>";

		}

		i++;

	}

	table = table + tr + "</table>";

	document.getElementById("listDir").innerHTML = table; // 将获取到的目录添加到目录列表<div>中

	// 获取图片文件

	var j = 0;

	imglist = "";

	for (; !eFiles.atEnd(); eFiles.moveNext()) {

		var itemFile = eFiles.item(); // 获取其中的一个文件

		if (itemFile.Type.indexOf("JPG") != -1
				|| itemFile.Type.indexOf("JPEG") != -1
				|| itemFile.Type.indexOf("GIF") != -1) {

			imglist = imglist
					+ "<div style='width:160px;float:left'>"
					+

					"<input name='sourceImg' type='checkbox' id='sourceImg' class='noborder' value='"
					+ itemFile +

					"'><img src='" + itemFile + "' alt='" + itemFile.name
					+ "' width='128' height='94' border='1'></div>";

			j++;

		}

	}

	document.getElementById("listFile").innerHTML = imglist; // 将获取的图片文件显示到“源文件”列表中

}
