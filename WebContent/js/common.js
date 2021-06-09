// 確認ダイアログの表示
function showConfirmDialog(message) {
	let result = confirm(message + "します。よろしいですか？");
	if (result == true) {
		return true;
	}
	return false;
}
