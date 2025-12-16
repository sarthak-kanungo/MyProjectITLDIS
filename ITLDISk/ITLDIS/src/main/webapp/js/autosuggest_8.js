/* Auto-suggest/auto-complete control
 *
 * original code:
 * (C) 2004-2005 zichun
 *
 * fixes, modifications and support:
 * (C) 2007-2009 Dmitriy Khudorozhkov (dmitrykhudorozhkov@yahoo.com) and contributors.
 *
 * This software is provided "as-is", without any express or implied warranty.
 * In no event will the author be held liable for any damages arising from the
 * use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */ 

/* Auto-suggest/auto-complete control
 *
 * original code:
 * (C) 2004-2005 zichun
 *
 * fixes, modifications and support:
 * (C) 2007-2009 Dmitriy Khudorozhkov (dmitrykhudorozhkov@yahoo.com) and contributors.
 *
 * This software is provided "as-is", without any express or implied warranty.
 * In no event will the author be held liable for any damages arising from the
 * use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */

var suggesturl = ""; // Global link to the server-side script, that gives you the suggestion list.
                     // Used for controls that do not define their own server script urls.
// pre-load images:
var image = new Array(4);

image[0] = new Image(), image[1] = new Image(),
image[2] = new Image(), image[3] = new Image();

image[0].src = "arrow-down.gif", image[1].src = "arrow-down-d.gif";
image[2].src = "arrow-up.gif",   image[3].src = "arrow-up-d.gif";
var img_obj=null;
var text_id="";
var row="";
function act8(id, ca, url,img,rowId)
{
    img_obj=img;
    text_id=id;
    row=rowId;
	// Public Variables:
        this.act8_suggesturl  = url || (ca ? "" : suggesturl);

	this.act8_timeOut     = -1;			// autocomplete Timeout in ms (-1: autocomplete never time out)
	this.act8_response    = 500;		// time, in milliseconds, between the last char typed and the actual query
	this.act8_lim         = 10;			// number of elements autocomplete will show

	this.act8_firstText   = true;		// should the auto complete be limited to the beginning of keyword?
	this.act8_firstMatch  = true;		// if previous is false, should the exact matches be displayed first?
	this.act8_fullRefresh = false;		// should the script re-send the AJAX request after each entered character?

	this.act8_useIFrame   = true;		// should the control use an IFrame element to fix suggestion list positioning (MS IE only)?
	this.act8_useScroll   = true;		// should the control use a scroll bar (true) or a up/down buttons (false)?
	this.act8_mouse       = true;		// enable mouse support
	this.act8_noDefault   = true;		// should the control omit selecting the 1st item in a suggestion list?
	this.act8_startcheck  = 0;			// show widget only after this number of characters is typed in.

	this.act8_delimiter   = [";", ","];	// delimiter for multiple autocomplete. Set it to empty array ( [] ) for single autocomplete
	this.ajax_delimiter   = "|"; 		// character that delimits entries in the string returned by AJAX call
	this.item_delimiter   = ","; 		// character that delimits key and value for the suggestion item in the string returned by AJAX call

	this.act8_selectedIndex = -1;		// index (zero-based) of the element last chosen

	// Styles:
	this.act8_arColor    = "#656291";  // background color for the "arrows"
	this.act8_bgColor    = "#FFFFF0";
	this.act8_textColor  = "#000";
	this.act8_htextColor = "#F00";
	this.act8_hColor     = "#D6D7E7";
	this.act8_fFamily    = "verdana,arial,helvetica";
	this.act8_arrowSize  = "7px";
	this.act8_fSize      = "10px";

	// "Private" Variables:
	this.act8_delimwords = [];
	this.act8_cdelimword = 0;
	this.act8_delimchar  = [];
	this.act8_display    = false;

	this.act8_pos    = 0;
	this.act8_total  = 0;
	this.act8_rangeu = 0;
	this.act8_ranged = 0;
	this.act8_bool   = [];
	this.act8_pre    = 0;
	this.act8_toid   = 0;
	this.act8_tomake = false;

	this.cur_x = 0;
	this.cur_y = 0;
	this.cur_w = 0;
	this.cur_h = 0;

	this.act8_mouse_on_list = 0;
	this.act8_caretmove     = false;

	this.act8_base_id  = id;
	this.act8_curr     = document.getElementById(id);
	this.act8_prevterm = this.act8_curr.value;

	this.act8_keywords = [];
	this.act8_values   = [];

	this.act8_keywords_init = [];
	this.act8_values_init   = [];

	ca = ca || [];
	for(var i = 0, cl = ca.length; i < cl; i++)
	{
		if(String(typeof(ca[i])).toLowerCase() == "string")
		{
			this.act8_keywords[i] = this.act8_keywords_init[i] = ca[i];
			this.act8_values[i]   = this.act8_values_init[i]   = "";
		}
		else
		{
			this.act8_keywords[i] = this.act8_keywords_init[i] = ca[i][0];
			this.act8_values[i]   = this.act8_values_init[i]   = ca[i][1];
		}
	}

	return this.construct();
};

act8.prototype = {

	callLater: function(func, obj)
	{ return function() { func.call(obj) }; },

	construct: function()
	{
		this.act8_curr.act8 = this;

		// pre-create event functions
		this.funcClick = this.act8_mouseclick;
		this.funcCheck = this.act8_checkkey;

		this.funcHighlight = this.act8_table_highlight;

		this.funcClear = this.callLater(this.act8_clear,    this);
		this.funcPress = this.callLater(this.act8_keypress, this);

		this.funcUp   = this.callLater(this.act8_goup,   this);
		this.funcDown = this.callLater(this.act8_godown, this);

		this.funcFocus   = this.callLater(this.act8_table_focus,   this);
		this.funcUnfocus = this.callLater(this.act8_table_unfocus, this);

		addEvent(this.act8_curr, "focus", this.callLater(this.act8_setup, this));

		return this;
	},

	act8_setup: function()
	{
		addEvent(document,       "keydown",  this.funcCheck);
		addEvent(this.act8_curr, "blur",     this.funcClear);
		addEvent(document,       "keypress", this.funcPress);
	},

	act8_clear: function()
	{
		var msie  = (document.all && !window.opera) ? true : false;
		var event = window.event;

		if(msie && event && this.cur_h)
		{
			var x = event.clientX, y = event.clientY;

			y += document.body.scrollTop;
			x += document.body.scrollLeft;

			if(((x > this.cur_x) && (x < (this.cur_x + this.cur_w))) && ((y > this.cur_y) && (y < (this.cur_y + this.cur_h))))
			{
				this.act8_curr.focus();
				return;
			}
		}

		removeEvent(document,       "keydown",  this.funcCheck);
		removeEvent(this.act8_curr, "blur",     this.funcClear);
		removeEvent(document,       "keypress", this.funcPress);

		this.act8_removedisp();
	},

	act8_parse: function(n)
	{
		if(!n || !n.length) return n;

	    var t, plen;
		if(this.act8_delimiter.length > 0)
		{
			   t = this.act8_delimwords[this.act8_cdelimword].trim().addslashes();
			plen = this.act8_delimwords[this.act8_cdelimword].trim().length;
		}
		else
		{
			   t = this.act8_curr.value.addslashes();
			plen = this.act8_curr.value.length;
		}

		if(!plen) return n;

		var tobuild = [];
		var c = 0;

		var re = this.act8_firstText ? new RegExp("^" + t, "i") : new RegExp(t, "i");
		var p = n.search(re);

		tobuild[c++] = n.substr(0, p);

		tobuild[c++] = "<u><font face='" + this.act8_fFamily + "'>";

		tobuild[c++] = n.substring(p, plen + p);

		tobuild[c++] = "</font></u>";

		tobuild[c++] = n.substring(plen + p, n.length);

		return tobuild.join("");
	},

	act8_generate: function()
	{
		var body = document.getElementById("tat_table_" + this.act8_base_id);

		if(body)
		{
			this.act8_display = false;
			document.body.removeChild(body);

			var helper = document.getElementById("tat_helper_" + this.act8_base_id);
			if(helper)
				document.body.removeChild(helper);
		}

		if(this.act8_total == 0)
		{
			this.act8_display = false;
			return;
		}

		var msie = (document.all && !window.opera) ? true : false;

		var bb = document.createElement("div");
		bb.id  = "tat_table_" + this.act8_base_id;
		bb.style.position = "absolute";
		bb.style.border = "#000000 solid 1px";
		bb.style.zIndex = 100;

		this.cur_y = curTop(this.act8_curr) + this.act8_curr.offsetHeight;
		bb.style.top = this.cur_y + "px";

		this.cur_x = bb.style.left = curLeft(this.act8_curr);
		bb.style.left = this.cur_x + "px";

		this.cur_w = this.act8_curr.offsetWidth - (msie ? 0 : 2);
		bb.style.width = this.cur_w + "px";

		this.cur_h = 1;
		bb.style.height = "1px"

		var cc = null;
		if(msie && this.act8_useIFrame)
		{
			var cc = document.createElement("iframe");
			cc.id  = "tat_helper_" + this.act8_base_id;

			cc.src = "javascript:'<html></html>';";
			cc.scrolling = "no";
			cc.frameBorder = "no";

			cc.style.display = "block";
			cc.style.position = "absolute";

			cc.style.zIndex = 99;
			cc.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=0)";
		}

		var act8_str = [];
		var cn = 0;

		act8_str[cn++] = "<table cellspacing='1px' cellpadding='2px' style='width:100%;background-color:" + this.act8_bgColor + "' id='tat_table2_" + this.act8_base_id + "'>";

		if(this.act8_useScroll && (this.act8_total > this.act8_lim))
		{
			this.cur_h = this.act8_lim * parseInt(this.act8_fSize);
			bb.style.height = this.cur_h + "px";

			bb.style.overflow  = "auto";
			bb.style.overflowX = "hidden";
		}

		if(cc)
		{
			document.body.appendChild(cc);

			cc.style.top  = this.cur_y + "px";
			cc.style.left = this.cur_x + "px";

			cc.style.width  = bb.offsetWidth + 2;
		}
		document.body.appendChild(bb);

		var counter = 0, first = true, j = 1;

		for(var i = 0; i < this.act8_keywords.length; i++)
		{
			if(!this.act8_useScroll && ((this.act8_keywords.length > this.act8_lim) && (this.act8_total > this.act8_lim) && !i))
			{
				act8_str[cn++] = "<tr style='background-color:" + this.act8_arColor + "'>";
				act8_str[cn++] = "<td style='color:" + this.act8_textColor + ";font-family:arial narrow;font-size:" + this.act8_arrowSize + ";cursor:default" + "' align='center'></td></tr>";
			}

			if(this.act8_bool[i] && (this.act8_useScroll || (counter < this.act8_lim)))
			{
				counter++;

				act8_str[cn++] = "<tr style='background-color:";

				var tcolor = this.act8_textColor;

				if((first && !this.act8_noDefault && !this.act8_tomake) || (this.act8_pre == i))
				{
					act8_str[cn++] = this.act8_hColor;
					tcolor = this.act8_htextColor;
					first = false;
				}
				else
				{
					act8_str[cn++] = this.act8_bgColor;
				}
				act8_str[cn++] = "' id='tat_tr_" + this.act8_base_id + String(j) + "'>";

				act8_str[cn++] = "<td style='color:" + tcolor + ";font-family:" + this.act8_fFamily + ";font-size:" + this.act8_fSize + ";white-space:nowrap" + "'>" + this.act8_parse(this.act8_keywords[i]) + "</td></tr>";

				j++;
			}
		}

		if(!this.act8_useScroll && (this.act8_total > this.act8_lim))
		{
			act8_str[cn++] = "<tr style='background-color:" + this.act8_arColor + "'>";
			act8_str[cn++] = "<td style='color:" + this.act8_textColor + ";font-family:arial narrow;font-size:" + this.act8_arrowSize + ";cursor:default" + "' align='center'></td></tr>";
		}

		bb.innerHTML = act8_str.join("");

		var table = bb.firstChild, row_num = table.rows.length, counter = 0, j = 1, real_height = 0, real_width = 0;
		if(this.act8_mouse)
		{
			table.onmouseout  = this.funcUnfocus;
			table.onmouseover = this.funcFocus;
		}

		for(i = 0; i < row_num; i++)
		{
			var row  = table.rows[i];
			var cell = row.cells[0];

			if(!this.act8_useScroll && ((this.act8_keywords.length > this.act8_lim) && (this.act8_total > this.act8_lim) && !i))
			{
				replaceHTML(cell, image[3]);

				// Up arrow:
				real_height += row.offsetHeight + 1;
			}
			else if((i == (row_num - 1)) && (!this.act8_useScroll && (this.act8_total > this.act8_lim)))
			{
				replaceHTML(cell, image[0]);

				// Down arrow:
				addEvent(cell, "click", this.funcDown);

				real_height += row.offsetHeight + 1;
			}
			else
			{
				counter++;

				// Content cells:
				cell.act8 = this;
				cell.setAttribute("pos", j);

				if(counter <= this.act8_lim)
					real_height += row.offsetHeight + 1;

				if(real_width < row.offsetWidth)
					real_width = row.offsetWidth;

				if(this.act8_mouse)
				{
					cell.style.cursor = msie ? "hand" : "pointer";
					addEvent(cell, "click", this.funcClick);
					cell.onmouseover = this.funcHighlight;
				}

				j++;
			}
		}

		real_height += (msie ? 3 : 1);
		this.cur_h = real_height;
		bb.style.height = real_height + "px";

		this.cur_w = (real_width > bb.offsetWidth ? real_width : bb.offsetWidth) + 2;
		bb.style.width  = this.cur_w + "px";

		if(cc)
		{
			this.cur_h = real_height;

			cc.style.height = bb.style.height = this.cur_h + "px";
			cc.style.width  = this.cur_w + "px";
		}

		this.act8_pos    = this.act8_noDefault ? 0 : 1;
		this.act8_rangeu = 1;
		this.act8_ranged = j - 1;
		this.act8_display = true;

	},

	act8_remake: function()
	{
		var msie = (document.all && !window.opera) ? true : false;
		var a = document.getElementById("tat_table2_" + this.act8_base_id);

		if(this.act8_mouse)
		{
			a.onmouseout  = this.funcUnfocus;
			a.onmouseover = this.funcFocus;
		}

		var i, k = 0;
		var first = true;
		var j = 1;

		if(this.act8_total > this.act8_lim)
		{
		    var b = (this.act8_rangeu > 1);

			var r = a.rows[k++];
			r.style.backgroundColor = this.act8_arColor;

			var c = r.firstChild;
			c.style.color = this.act8_textColor;
			c.style.fontFamily = "arial narrow";
			c.style.fontSize = this.act8_arrowSize;
			c.style.cursor = "default";
			c.align = "center";

			replaceHTML(c, b ? image[2] : image[3]);

			if(b)
			{
				addEvent(c, "click", this.funcUp);
				c.style.cursor = msie ? "hand" : "pointer";
			}
			else
			{
				c.style.cursor = "default";
			}
		}

		for(var i = 0; i < this.act8_keywords.length; i++)
		{
			if(this.act8_bool[i])
			{
				if(j >= this.act8_rangeu && j <= this.act8_ranged)
				{
					var r = a.rows[k++];
					r.style.backgroundColor = this.act8_bgColor;
					r.id = "tat_tr_" + this.act8_base_id + String(j);

					var c = r.firstChild;
					c.style.color = this.act8_textColor;
					c.style.fontFamily = this.act8_fFamily;
					c.style.fontSize = this.act8_fSize;
					c.innerHTML = this.act8_parse(this.act8_keywords[i]);
					c.setAttribute("pos", j);
				}

				j++;
			}

			if(j > this.act8_ranged) break;
		}

		if(this.act8_keywords.length > this.act8_lim)
		{
			var b = ((j - 1) < this.act8_total);

			var r = a.rows[k];
			r.style.backgroundColor = this.act8_arColor;

			var c = r.firstChild;
			c.style.color = this.act8_textColor;
			c.style.fontFamily = "arial narrow";
			c.style.fontSize = this.act8_arrowSize;
			c.style.cursor = "default";
			c.align = "center";

			replaceHTML(c, b ? image[0] : image[1]);

			if(b)
			{
				addEvent(c, "click", this.funcDown);
				c.style.cursor = msie ? "hand" : "pointer";
			}
			else
			{
				c.style.cursor = "default";
			}
		}

		if((document.all && !window.opera))
		{
			var helper = document.getElementById("tat_helper_" + this.act8_base_id);
			if(helper)
				helper.style.width  = a.parentNode.offsetWidth + 2;
		}
	},

	act8_goup: function()
	{
		this.act8_curr.focus();

		if(!this.act8_display) return;
		if(this.act8_pos <= 1) return;

		var t = document.getElementById("tat_tr_" + this.act8_base_id + String(this.act8_pos));
		if(t && t.style)
		{
			t.style.backgroundColor = this.act8_bgColor;
		    t.firstChild.style.color = this.act8_textColor;
		}

		this.act8_pos--;
		t = document.getElementById("tat_tr_" + this.act8_base_id + String(this.act8_pos));

		if(this.act8_useScroll && t)
		{
			var base = document.getElementById("tat_table_" + this.act8_base_id);
			base.scrollTop = t.offsetTop;
		}
		else
		{
			if(this.act8_pos < this.act8_rangeu)
			{
				this.act8_rangeu--;
				this.act8_ranged--;
				this.act8_remake();
			}

			t = document.getElementById("tat_tr_" + this.act8_base_id + String(this.act8_pos));
		}

		if(t && t.style)
		{
			t.style.backgroundColor = this.act8_hColor;
		    t.firstChild.style.color = this.act8_htextColor;
		}

		if(this.act8_toid)
		{
			clearTimeout(this.act8_toid);
			this.act8_toid = 0;
		}

		if(this.act8_timeOut > 0)
			this.act8_toid = setTimeout(function() { this.act8_mouse_on_list = 1; this.act8_removedisp(); }, this.act8_timeOut);

		this.act8_curr.focus();
	},

	act8_godown: function()
	{
		this.act8_curr.focus();

		if(!this.act8_display) return;
		if(this.act8_pos == this.act8_total) return;

		if(this.act8_pos >= 1)
		{
			var t = document.getElementById("tat_tr_" + this.act8_base_id + String(this.act8_pos));
			if(t && t.style)
			{
				t.style.backgroundColor = this.act8_bgColor;
				t.firstChild.style.color = this.act8_textColor;
			}
		}
		else this.act8_pos = 0;

		this.act8_pos++;
		t = document.getElementById("tat_tr_" + this.act8_base_id + String(this.act8_pos));

		if(this.act8_useScroll && t)
		{
			var base = document.getElementById("tat_table_" + this.act8_base_id);
			base.scrollTop = t.offsetTop;
		}
		else
		{
			if(this.act8_pos > this.act8_ranged)
			{
				this.act8_rangeu++;
				this.act8_ranged++;
				this.act8_remake();
			}

			t = document.getElementById("tat_tr_" + this.act8_base_id + String(this.act8_pos));
		}

		if(t && t.style)
		{
			t.style.backgroundColor = this.act8_hColor;
			t.firstChild.style.color = this.act8_htextColor;
		}

		if(this.act8_toid)
		{
			clearTimeout(this.act8_toid);
			this.act8_toid = 0;
		}

		if(this.act8_timeOut > 0)
			this.act8_toid = setTimeout(function() { this.act8_mouse_on_list = 1; this.act8_removedisp(); }, this.act8_timeOut);

		this.act8_curr.focus();
	},

	act8_mouseclick: function(event)
	{
		var elem = getTargetElement(event);
		if(!elem.id) elem = elem.parentNode;

		var obj = elem.act8;

		if(!obj)
		{
			var tag = elem.tagName.toLowerCase();
			elem = (tag == "tr") ? elem.firstChild : elem.parentNode;

			obj = elem.act8;
		}

		if(!obj || !obj.act8_display) return;

		obj.act8_mouse_on_list = 0;
		obj.act8_pos = elem.getAttribute("pos");
		obj.act8_penter();
                getPartPriceByPart(document.getElementById(text_id),row);
	},

	act8_table_focus: function()
	{ this.act8_mouse_on_list = 1; },

	act8_table_unfocus: function()
	{
		this.act8_mouse_on_list = 0;

		if(this.act8_toid)
		{
			clearTimeout(this.act8_toid);
			this.act8_toid = 0;
		}

		if(this.act8_timeOut > 0)
			this.act8_toid = setTimeout(function() { obj.act8_mouse_on_list = 0; this.act8_removedisp(); }, this.act8_timeOut);
	},

	act8_table_highlight: function(event)
	{
		var elem = getTargetElement(event);

		var obj = elem.act8;
		if(!obj) return;

		obj.act8_mouse_on_list = 1;

		var row = document.getElementById("tat_tr_" + obj.act8_base_id + obj.act8_pos);
		if(row && row.style)
		{
			row.style.backgroundColor = obj.act8_bgColor;
			row.firstChild.style.color = obj.act8_textColor;
		}

		obj.act8_pos = elem.getAttribute("pos");

		row = document.getElementById("tat_tr_" + obj.act8_base_id + obj.act8_pos);
		if(row && row.style)
		{
			row.style.backgroundColor = obj.act8_hColor;
			row.firstChild.style.color = obj.act8_htextColor;
		}

		if(obj.act8_toid)
		{
			clearTimeout(obj.act8_toid);
			obj.act8_toid = 0;
		}

		if(obj.act8_timeOut > 0)
			obj.act8_toid = setTimeout(function() { obj.act8_mouse_on_list = 0; obj.act8_removedisp(); }, obj.act8_timeOut);
	},

 	act8_penter: function()
	{
		if(!this.act8_display) return;
		if(this.act8_pos < 1) return;

		this.act8_display = false;

		var word = "", c = 0;

		for(var i = 0; i < this.act8_keywords.length; i++)
		{
			if(this.act8_bool[i]) c++;

			if(c == this.act8_pos)
			{
				word = this.act8_keywords[i];
				break;
			}
		}

		this.act8_selectedIndex = c;
		this.act8_insertword(word);
	},

	act8_insertword: function(a)
	{
		if(this.act8_delimiter.length > 0)
		{
			var str = "";

			for(var i = 0; i < this.act8_delimwords.length; i++)
			{
				if(this.act8_cdelimword == i)
				{
					prespace = postspace = "";
					gotbreak = false;
					for(var j = 0; j < this.act8_delimwords[i].length; ++j)
					{
						if(this.act8_delimwords[i].charAt(j) != " ")
						{
							gotbreak = true;
							break;
						}

						prespace += " ";
					}

					for(j = this.act8_delimwords[i].length - 1; j >= 0; --j)
					{
						if(this.act8_delimwords[i].charAt(j) != " ") break;
						postspace += " ";
					}

					str += prespace;
					str += a;
					if(gotbreak) str += postspace;
				}
				else
				{
					str += this.act8_delimwords[i];
				}

				if(i != this.act8_delimwords.length - 1)
					str += this.act8_delimchar[i];
			}

			this.act8_curr.value = str;
			setCaret(this.act8_curr, this.act8_curr.value.length);
		}
		else
		{
			this.act8_curr.value = a;
		}

		this.act8_mouse_on_list = 0;
		this.act8_removedisp();
	},

	act8_removedisp: function()
	{
		if(this.act8_mouse_on_list == 0)
		{
			this.act8_display = 0;

			var base = document.getElementById("tat_table_" + this.act8_base_id);
			if(base)
			{
				var helper = document.getElementById("tat_helper_" + this.act8_base_id);
				if(helper)
					document.body.removeChild(helper);

				document.body.removeChild(base);
			}

			if(this.act8_toid)
			{
			  clearTimeout(this.act8_toid);
			  this.act8_toid = 0;
			}

			this.cur_x = 0;
			this.cur_y = 0;
			this.cur_w = 0;
			this.cur_h = 0;
		}
	},

	act8_keypress: function(event)
	{
		if(this.act8_caretmove) stopEvent(event);
		return !this.act8_caretmove;
	},

	act8_checkkey: function(event)
	{
		event = event || window.event;

		var code = event.keyCode;
		var obj = getTargetElement(event).act8;
		obj.act8_caretmove = 0;

		var term = "";

		if(obj.act8_toid)
		{
			clearTimeout(obj.act8_toid);
			obj.act8_toid = 0;
		}

		switch(code)
		{
                        // '*' is pressed.
                        case 106:
				if(!obj.act8_display)
				{
					obj.act8_toid = setTimeout(function()
					{
						obj.act8_tocomplete.call(obj, -1);
					},
					25);
				}

				return false;
				break;
                        // Up arrow:
			case 38:
				obj.act8_goup();
				obj.act8_caretmove = 1;
				return false;
				break;

			// Down arrow:
			case 40:
				if(!obj.act8_display)
				{
					obj.act8_toid = setTimeout(function()
					{
						obj.act8_tocomplete.call(obj, -1);
					},
					25);
				}
				else
				{
					obj.act8_godown();
					obj.act8_caretmove = 1;
				}
				return false;
				break;

			// Page up:
			case 33:
				for(var c = 0; c < obj.act8_lim; c++)
					obj.act8_goup();

				obj.act8_caretmove = 1;
				break;

			// Page down:
			case 34:
				for(var c = 0; c < obj.act8_lim; c++)
					obj.act8_godown();

				obj.act8_caretmove = 1;
				break;

			// Esc:
			case 27:
				term = obj.act8_curr.value;

				obj.act8_mouse_on_list = 0;
				obj.act8_removedisp();
				break;

			// Enter:
			case 13:
				if(obj.act8_display)
				{
					obj.act8_caretmove = 1;
					obj.act8_penter();
					return false;
				}
				break;

			// Tab:
			case 9:
				if((obj.act8_display && obj.act8_pos) || obj.act8_toid)
				{
					obj.act8_caretmove = 1;
					obj.act8_penter();

					setTimeout(function() { obj.act8_curr.focus(); }, 25);
					return false;
				}
				break;

			default:
				break;
		}

		if(term.length) setTimeout(function() { obj.act8_curr.value = term; }, 25);
		return true;
	},

	act8_tocomplete: function(kc)
	{
             //alert(img_obj);

		if(this.act8_toid)
		{
			clearTimeout(this.act8_toid);
			this.act8_toid = 0;
		}
		else
		{
			return;
		}

		if(this.act8_display && (this.act8_prevterm == this.act8_curr.value)) return;
		this.act8_prevterm = this.act8_curr.value;

		if(kc == 38 || kc == 40 || kc == 13) return;

		if(this.act8_display)
		{
			var word = 0;
			var c = 0;

			for(var i = 0; i <= this.act8_keywords.length; i++)
			{
				if(this.act8_bool[i]) c++;

				if(c == this.act8_pos)
				{
					word = i;
					break;
				}
			}

			this.act8_pre = word;
		}
		else
		{
			this.act8_pre = -1;
		}

		if(!this.act8_curr.value.length && (kc != -1))
		{
			this.act8_mouse_on_list = 0;
			this.act8_removedisp();
		}

		var ot, t;

		if(this.act8_delimiter.length > 0)
		{
			var caret_pos_end = this.act8_curr.value.length;

			var delim_split = "";
			for(var i = 0; i < this.act8_delimiter.length; i++)
				delim_split += this.act8_delimiter[i];

		    delim_split = delim_split.addslashes();
			var delim_split_rx = new RegExp("([" + delim_split + "])");
			c = 0;
			this.act8_delimwords = [];
			this.act8_delimwords[0] = "";

			for(var i = 0, j = this.act8_curr.value.length; i < this.act8_curr.value.length; i++, j--)
			{
				if(this.act8_curr.value.substr(i, j).search(delim_split_rx) == 0)
				{
					ma = this.act8_curr.value.substr(i,j).match(delim_split_rx);
					this.act8_delimchar[c] = ma[1];
					c++;
					this.act8_delimwords[c] = "";
				}
				else
				{
					this.act8_delimwords[c] += this.act8_curr.value.charAt(i);
				}
			}

			var l = 0;
			this.act8_cdelimword = -1;
			for(i = 0; i < this.act8_delimwords.length; i++)
			{
				if((caret_pos_end >= l) && (caret_pos_end <= l + this.act8_delimwords[i].length))
					this.act8_cdelimword = i;

				l += this.act8_delimwords[i].length + 1;
			}

			ot = this.act8_delimwords[this.act8_cdelimword].trim();
			 t = this.act8_delimwords[this.act8_cdelimword].addslashes().trim();
		}
		else
		{
			ot = this.act8_curr.value;
			 t = this.act8_curr.value.addslashes();
		}

		if(ot.length == 0 && (kc != -1))
		{
			this.act8_mouse_on_list = 0;
			this.act8_removedisp();
		}
		else if((ot.length == 1) || this.act8_fullRefresh ||
		       ((ot.length > 1) && !this.act8_keywords.length) ||
		       ((ot.length > 1) && (this.act8_keywords[0].substr(0, 1).toLowerCase() != ot.substr(0, 1).toLowerCase())))
		{
			var ot_ = ((ot.length > 1) && !this.act8_fullRefresh) ? ot.substr(0, 1) : ot;

			if(this.act8_suggesturl.length)
			{
				// create xmlhttprequest object:
				var http = null;
				if(typeof XMLHttpRequest != "undefined")
				{
					try
					{
						http = new XMLHttpRequest();
					}
					catch (e) { http = null; }
				}
				else
				{
					try
					{
						http = new ActiveXObject("Msxml2.XMLHTTP") ;
					}
					catch (e)
					{
						try
						{
							http = new ActiveXObject("Microsoft.XMLHTTP") ;
						}
						catch (e) { http = null; }
					}
				}

				if(http)
				{
					// For local debugging in Mozilla/Firefox:
					/*
					try
					{
						netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
					}
					catch (e) { }
					*/

					if(http.overrideMimeType)
						http.overrideMimeType("text/xml");

					http.open("POST", this.act8_suggesturl + ot_, true);

					var obj = this;
					http.onreadystatechange = function(n)
					{
						if(http.readyState == 4)
						{
							if((http.status == 200) || (http.status == 0))
							{
								var text = http.responseText;
                                                                var index1 = text.indexOf("<listdata>");
								var index2 = (index1 == -1) ? text.length : text.indexOf("</listdata>", index1 + 10);
								if(index1 == -1)
									index1 = 0;
								else
									index1 += 10;

								var tmpinfo = text.substring(index1, index2);
                                                                 if(tmpinfo=='')
                                                                     {
                                                                         img_obj.style.visibility="hidden";
                                                                         document.getElementById("msg_saveFAILED").innerHTML='No Suggestions available.';
                                                                         document.getElementById(text_id).value='';
                                                                     }

                                                                if(tmpinfo)
								{
									obj.act8_keywords = tmpinfo.split(obj.ajax_delimiter);

									if(obj.item_delimiter && obj.item_delimiter.length)
									{
										var keyword_number = obj.act8_keywords.length;
										for(var i = 0; i < keyword_number; i++)
										{
											var ca = obj.act8_keywords[i], comma = ca.indexOf(obj.item_delimiter);

											if(comma != -1)
											{
												var ci = ca.split(",");

												obj.act8_keywords[i] = obj.act8_keywords_init[i] = ci[0];
												obj.act8_values[i]   = obj.act8_values_init[i]   = ci[1];
											}
											else
											{
												obj.act8_values[i] = obj.act8_values_init[i] = "";
											}
										}
									}

									obj.done.call(obj, ot_, t);
                                                                        img_obj.style.visibility="hidden";
								}

							}
						}
					}
                                        //img_obj.style.visibility="hidden";
					http.send(null);
				}

				// xmlhttp object creation failed
				return;
			}
			else
			{
				this.done(ot, t);
			}
		}
		else
		{
			this.done(ot, t);
		}
	},

	done: function(ot, t)
	{
		if(ot.length < this.act8_startcheck) return;

		var re = new RegExp(((!this.act8_firstText && !this.act8_firstMatch) ? "" : "^") + t, "i");

		this.act8_total = 0;
		this.act8_tomake = false;

		var al = this.act8_keywords.length;

		for(var i = 0; i < al; i++)
		{
			this.act8_bool[i] = false;
			if(re.test(this.act8_keywords[i]))
			{
				this.act8_total++;
				this.act8_bool[i] = true;

				if(this.act8_pre == i) this.act8_tomake = true;
			}
		}

		if(!this.act8_curr.value.length)
		{
			for(i = 0; i < al; i++)
			{
				this.act8_keywords[i] = this.act8_keywords_init[i];
				this.act8_values[i] = this.act8_values_init[i];
				this.act8_bool[i] = true;
			}
		}
		else if(!this.act8_firstText && this.act8_firstMatch)
		{
			var tmp = [], tmpv = [];

			for(i = 0; i < al; i++)
			{
				if(this.act8_bool[i])
				{
					tmp[tmp.length]   = this.act8_keywords[i];
					tmpv[tmpv.length] = this.act8_values[i];
				}
			}

			re = new RegExp(t, "i");

			for(i = 0; i < al; i++)
			{
				if(re.test(this.act8_keywords[i]) && !this.act8_bool[i])
				{
					this.act8_total++;
					this.act8_bool[i] = true;

					if(this.act8_pre == i) this.act8_tomake = true;

					tmp[tmp.length]   = this.act8_keywords[i];
					tmpv[tmpv.length] = this.act8_values[i];
				}
			}

			for(i = 0; i < al; i++)
			{
				if(!this.act8_bool[i])
				{
					tmp[tmp.length]   = this.act8_keywords[i];
					tmpv[tmpv.length] = this.act8_values[i];
				}
			}

			for(i = 0; i < al; i++)
			{
				this.act8_keywords[i] = tmp[i];
				this.act8_values[i]   = tmpv[i];
			}

			for(i = 0; i < al; i++)
				this.act8_bool[i] = (i < this.act8_total) ? true : false;
		}

		if(this.act8_timeOut > 0)
		  this.act8_toid = setTimeout(function(){ this.act8_mouse_on_list = 0; this.act8_removedisp(); }, this.act8_timeOut);

		this.act8_generate();
	}
}

// Supplementary functions

// Add an event to the obj given
// event_name refers to the event trigger, without the "on", like click or mouseover
// func_name refers to the function callback when event is triggered
function addEvent(obj, event_name, func_ref)
{
//	if(obj.addEventListener && !window.opera)
//	{
//		obj.addEventListener(event_name, func_ref, true);
//	}
//	else
//	{
		obj["on" + event_name] = func_ref;
	//}
}

// Removes an event from the object
function removeEvent(obj, event_name, func_ref)
{
	if(obj.removeEventListener && !window.opera)
	{
		obj.removeEventListener(event_name, func_ref, true);
	}
	else
	{
		obj["on" + event_name] = null;
	}
}

// Stop an event from bubbling up the event DOM
function stopEvent(event)
{
	event = event || window.event;

	if(event)
	{
		if(event.stopPropagation) event.stopPropagation();
		if(event.preventDefault) event.preventDefault();

		if(typeof event.cancelBubble != "undefined")
		{
			event.cancelBubble = true;
			event.returnValue = false;
		}
	}

	return false;
}

// Get the obj that triggers off the event
function getTargetElement(event)
{
	event = event || window.event;
	return event.srcElement || event.target;
}

// Sets the caret position to l in the object
function setCaret(obj, l)
{
	obj.focus();

	if(obj.setSelectionRange)
	{
		obj.setSelectionRange(l, l);
	}
	else if(obj.createTextRange)
	{
		m = obj.createTextRange();
		m.moveStart("character", l);
		m.collapse();
		m.select();
	}
}

// String functions
String.prototype.addslashes = function() { return this.replace(/(["\\\.\|\[\]\^\*\+\?\$\(\)])/g, "\\$1"); }

String.prototype.trim = function () { return this.replace(/^\s*(\S*(\s+\S+)*)\s*$/, "$1"); };

// Offset position from top of the screen
function curTop(obj)
{
	var toreturn = 0;
	while(obj)
	{
		toreturn += obj.offsetTop;
		obj = obj.offsetParent;
	}

	return toreturn;
}

// Offset position from left of the screen
function curLeft(obj)
{
	var toreturn = 0;
	while(obj)
	{
		toreturn += obj.offsetLeft;
		obj = obj.offsetParent;
	}

	return toreturn;
}

// Image installation
function replaceHTML(obj, oImg)
{
	var el = obj.childNodes[0];
	while(el)
	{
		obj.removeChild(el);
		el = obj.childNodes[0];
	}

	obj.appendChild(oImg);
}