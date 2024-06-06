import BlogpostService from "../../service/BlogpostService"
import '../../App.css';
import React, { useEffect, useRef, useState } from "react";
import {useNavigate} from "react-router-dom";
import SideBar from "../communal/SideBar";
import {
    Card,
    CardBody,
    CardHeader,
    Heading,
    Stack,
    Wrap,
    WrapItem,
    StackDivider,
    Button,
    Flex,
    Drawer,
    DrawerBody,
    DrawerFooter,
    DrawerHeader,
    DrawerOverlay,
    DrawerContent,
    DrawerCloseButton,
    useDisclosure,
    Input, FormControl, FormLabel, Select
} from "@chakra-ui/react";
import PostCard from "./PostCard";
import {useUser} from "../../service/UserContext";

const Posts = () => {
    const [posts, setPosts] = useState(null);
    const [loading, setLoading] = useState(true);
    const [author, setAuthor] = useState("");
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [category, setCategory] = useState("");
    const { isOpen, onOpen, onClose } = useDisclosure();
    const btnRef = useRef()
    const navigate = useNavigate();

    const { user } = useUser();

    const fetchPostsData = async () => {
        setLoading(true);
        try {
            const postData = await BlogpostService.getPosts();
            console.log(postData);
            setPosts(postData);
        } catch (e) {
            console.log(e);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        if (!posts) fetchPostsData();
        console.log("the current user is: " + JSON.stringify(user));
    }, [posts])

    const savePost = async () => {
        if (!user) throw "Please Login and create the post";
        console.log("author: " + author + " title: " + title + " description: " + description + " category: " + category);
        const res = await BlogpostService.savePost(author, title, description, category);
        await console.log(res);
        navigate(0);
    }

    if (loading) return <div>Loading...</div>

    return (
        <div className="App">
            <SideBar />
            <Card>
                <CardHeader>
                    <Flex width="100%" justify="space-between" align="center" position="relative">
                        <Heading size='md' mx='auto'>All Posts</Heading>
                        <Button ref={btnRef} colorScheme='teal' onClick={onOpen} style={{ position: 'absolute', right: '10px' }}>Add Post</Button>
                        <Drawer
                            isOpen={isOpen}
                            placement='right'
                            onClose={onClose}
                            finalFocusRef={btnRef}
                        >
                            <DrawerOverlay />
                            <DrawerContent>
                                <DrawerCloseButton />
                                <DrawerHeader>Add a Post</DrawerHeader>
                                <DrawerBody>
                                    <FormControl>
                                        <FormLabel>Author</FormLabel>
                                        <Input
                                            id='author'
                                            name='author'
                                            value={user?.email || ''}
                                            isReadOnly
                                        />
                                    </FormControl>
                                    <FormControl mt={4}>
                                        <FormLabel>Title</FormLabel>
                                        <Input
                                            id='title'
                                            name='title'
                                            placeholder='Title'
                                            onChange={(e) => setTitle(e.target.value)}
                                        />
                                    </FormControl>
                                    <FormControl mt={4}>
                                        <FormLabel>Description</FormLabel>
                                        <Input
                                            id='description'
                                            name='description'
                                            placeholder='Description'
                                            onChange={(e) => setDescription(e.target.value)}
                                        />
                                    </FormControl>
                                    <FormControl mt={4}>
                                        <FormLabel>Category</FormLabel>
                                        <Select
                                            id='category'
                                            placeholder='Select a category'
                                            onChange={(e) => setCategory(e.target.value)}
                                        >
                                            <option value='math'>Math</option>
                                            <option value='english'>English</option>
                                            <option value='other'>Other</option>
                                        </Select>
                                    </FormControl>
                                </DrawerBody>
                                <DrawerFooter>
                                    <Button variant='outline' mr={3} onClick={onClose}>
                                        Cancel
                                    </Button>
                                    <Button type='submit' colorScheme='blue' onClick={savePost}>Save</Button>
                                </DrawerFooter>
                            </DrawerContent>
                        </Drawer>
                    </Flex>
                </CardHeader>
                {posts.data.map((post, index) => (
                    <CardBody key={index}>
                        <Stack divider={<StackDivider />} spacing='4'>
                            <PostCard {...post} />
                        </Stack>
                    </CardBody>
                ))}
            </Card>
        </div>

    );
}

export default Posts;