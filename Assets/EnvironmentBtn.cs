using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;

public class EnvironmentBtn : MonoBehaviour
{

    public TMP_Text NameText;
    public Image PreviewImage;

    void Setup(string name, string imageURL)
    {
        PreviewImage.gameObject.SetActive(false);
        if(imageURL.Trim() != "")
            LoadUrl(imageURL);
    }

    IEnumerator LoadUrl(string url)
    {
        WWW www = new WWW(url);
        yield return www;
        PreviewImage.sprite = Sprite.Create(www.texture, new Rect(0, 0, www.texture.width, www.texture.height), new Vector2(0, 0));
        PreviewImage.gameObject.SetActive(true);
    }
}
